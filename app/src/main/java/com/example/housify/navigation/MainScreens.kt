package com.example.housify.navigation

/**
 * The class for navigation in main screens.
 */
sealed class MainScreens(val route: String) {
    object Home : MainScreens("home_screen")
    object Details : MainScreens("details_screen")
}