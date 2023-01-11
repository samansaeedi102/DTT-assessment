package com.example.housify.navigation

sealed class MainScreens(val route: String) {
    object Splash: MainScreens("splash_screen")
    object Home: MainScreens("home_screen")
    object Details: MainScreens("details_screen")
}