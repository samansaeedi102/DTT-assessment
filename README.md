# DTT-assessment
### Housify 
###### An application in which a list of houses is load in home screen and user sees all the details of each house and the distance of house to the user is also shown. If the user clicks on one of the houses, screen will navigate to the details of the chosen house. In this screen they can also see the location on map and by clicking on the map they can go to Google App and see the best way to get to the chosen house.
### Navigation
###### Since user must not see the Bottom bar when they navigate to details screen, I used 2 navcontrollers. one for main screens(home, details) and one for bottom(home, about).
### API
###### In order to fetch the data from API I used Retrofit library and for loading the images I used Coil.
### Dependency Injection(Manual)
###### At first, I used manual dependency injection which seems to be slows because when user searched a house, before navigating to the details, for a very short milliseconds the empty result screen was shown and then the app navigated to the result screen.
### Dependency Injection(Dagger-Hilt)
###### Then I implemented Dagger-Hilt and thanks to the speed of this library, the mentioned bug was obviated and app navigated to the house details screen without any interruption.
### Google map
###### I used Google Map library and composable to load maps in details screens.
### Distance to current user
###### In order to calculate the distance between the house and current user, I got the permission from the user in order to get their location and calculated distance between to locations based on Latitude and Longitude.
### MVVM
###### For saving the state of the application in recompositions, I used MVVM architecture which uses ViewModel to save the state of the flow. 
