# carapp
Car application


This project is a simple example of MVVM pattern using ViewModel, Livedata, Retrofit, RxJava. 

The example uses Free API for fetching data.
This example contains different screens like -Login, Registration, Main Page that contains user location and car locations, Profile page for fetching profile data

Below is the architecture diagram(Image references::https://developer.android.com/jetpack/docs/guide)
![](Images/mvvm.PNG)

<img src="Images/mvvm.png" height="550" width="550">

Below are the screenshots of the image
1) Splash Page-

<img src="Images/carapp_splash.jpg" height="550" width="280">



2) Login Page-

<img src="Images/carapp_login.jpg" height="550" width="280">



3) Registration Page-

<img src="Images/carapp_signup.jpg" height="550" width="280">



4) Main Page showing locations of all vehicles, including custom image(custom marker) of the vehicle. Once user clicks on the vehicle, license plate number of the vehicle is shown-

<img src="Images/carapp_allcars.jpg" height="550" width="280">



5) Main page showing current location of the user. When 'Locate User' button is clicked, map will point to the current location of user and all the markers of the vehicles on the screen is removed-

<img src="Images/carapp_mylocation.jpg" height="550" width="280">



6) Profile page. When user clicks on 'Logout' button, user is logged out of the application and redirected to the login page-

<img src="Images/carapp_user.jpg" height="550" width="280">
