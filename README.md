# SwapSense
SwapSense : Face Swap, using sensors

This is an Android App called SwapSenseApp with three main functionalities that users can navigate
between using the bottom navigation bar:
1) The sensor tab which allows to read data from 3 chosen sensors: accelerometer, gyroscope and light
sensor. When the users open the app, they will land on this page as default page and will be ask for
permission to access and read data from three sensors mentioned above.
2) The face swap tab allows user to upload 2 images and swap the faces on those two images. When
user upload an image, the app will detect the existence of face in the image, if there is no found
of human faces in the image, a message "Can't detect face in the uploaded image" will let users know.
3) The camera tab will allow user to capture photo and display on the screen. They will also have
the option to save the captured photo to gallery or delete it from displaying the screen. Users also
able to upload and view the image by accessing and choosing the local gallery on their phone. There
will be some permission regarding to access photo library and camera usage for the users who spin the app
for the first time.

It took me 30 hours to finish this app.

The most challenging part of this app is the swap face tab where I have to debug a lot on how to use
the ML package and displaying the swapped face images correctly.

This is the Figma Design of the sensors page: 
https://www.figma.com/file/UKA0TK1yF9wpuNNDLyTzS4/HW2-Bonus?type=design&node-id=0%3A1&mode=design&t=xji1DexcpSyZW040-1