package com.example.housify.navigation

sealed class Screen(val route: String) {
    object Splash: Screen("splash_screen")
    object Home: Screen("home_screen")
    object Search: Screen("search_screen")
    object Details: Screen("details_screen")
    object About: Screen("about_screen")
}