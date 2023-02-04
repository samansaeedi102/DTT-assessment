package com.example.housify.navigation

//The class for navigation by main navigation
sealed class MainScreens(val route: String) {
//    object Splash: MainScreens("splash_screen")
    object Home: MainScreens("home_screen")
    object Details: MainScreens("details_screen")
}