package com.example.swapsense.ui.dashboard

import android.Manifest
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.swapsense.databinding.FragmentDashboardBinding
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService


class DashboardFragment : Fragment(), SensorEventListener{

    private lateinit var acceleratorTV: TextView
    private lateinit var gyroscopeTV: TextView
    private lateinit var lightTV: TextView
    private lateinit var sensorManager: SensorManager
    private var acceleratorData: Float = 0.0F
    private var gyroscopeData: Float = 0.0F
    private var lightData: Float = 0.0F

    private var _binding: FragmentDashboardBinding? = null

    // TextViews to display sensor data
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO
        // Initialize TextViews from the layout
        acceleratorTV = binding.textViewAccelerometer
        gyroscopeTV = binding.textViewGyroscope
        lightTV = binding.textViewLight

        // TODO
        // Get the SensorManager instance
        // Get list of all Sensors
        // LOG it
        sensorManager = requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL)
        Log.d("ALL SENSORS LIST", deviceSensors.toString())

        // TODO
        // Get sensor
        val acceleratorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        val lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        // TODO
        // Check if Sensors available
        // Toast Message if Sensor not available
        if (acceleratorSensor == null) Toast.makeText(requireContext(),"Accelerator Sensor is not available!", Toast.LENGTH_SHORT).show()
        if (gyroscopeSensor == null) Toast.makeText(requireContext(),"Gyroscope Sensor is not available!", Toast.LENGTH_SHORT).show()
        if (lightSensor == null) Toast.makeText(requireContext(),"Light Sensor is not available!", Toast.LENGTH_SHORT).show()

        // TODO
        // Define SensorEventListeners

        // TODO
        // Register listeners
        sensorManager.registerListener(this, acceleratorSensor, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL)

    }

    // TODO
    // checkPermission for the SENSORS
    private fun checkPermission(requestCode: Int) {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.BODY_SENSORS) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request it
            requestPermissions(arrayOf(Manifest.permission.BODY_SENSORS), requestCode)
        } else {
            // Permission is already granted, proceed with your logic
            // TODO: Implement logic for accelerometer
            when (requestCode) {
                REQUEST_CODE_ACCELEROMETER -> {
                    acceleratorTV.text = "Accelerator: " + acceleratorData
                }
                REQUEST_CODE_GYROSCOPE -> {
                    gyroscopeTV.text = "Gyroscope: " + gyroscopeData
                }
                REQUEST_CODE_LIGHT -> {
                    lightTV.text = "Light: " + lightData
                }
            }
        }
    }


    // TODO
    // Callback for the result from requesting permissions
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_ACCELEROMETER -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted, proceed with your logic
                    // TODO: Implement logic for accelerometer
                    acceleratorTV.text = "Accelerator: " + acceleratorData
                }
            }
            REQUEST_CODE_GYROSCOPE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted, proceed with your logic
                    // TODO: Implement logic for gyroscope
                    gyroscopeTV.text = "Gyroscope: " + gyroscopeData
                }
            }
            REQUEST_CODE_LIGHT -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted, proceed with your logic
                    // TODO: Implement logic for light sensor
                    lightTV.text = "Light: " + lightData
                }
            }
        }
    }


    // TODO
    // Declare Request codes for permissions

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSensorChanged(event: SensorEvent) {
        if(event.sensor.type == Sensor.TYPE_ACCELEROMETER){
            //Update UI
            acceleratorData = event.values[0]
            checkPermission(REQUEST_CODE_ACCELEROMETER)
        }
        if(event.sensor.type == Sensor.TYPE_GYROSCOPE){
            //UpdateUI
            gyroscopeData = event.values[0]
            checkPermission(REQUEST_CODE_GYROSCOPE)
        }
        if(event.sensor.type == Sensor.TYPE_LIGHT){
            //UpdateUI
            lightData = event.values[0]
            checkPermission(REQUEST_CODE_LIGHT)
        }
    }

    companion object {
        private val REQUEST_CODE_ACCELEROMETER = 2001
        private val REQUEST_CODE_GYROSCOPE = 2002
        private val REQUEST_CODE_LIGHT = 2003
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
//        TODO("Not yet implemented")
    }
}
