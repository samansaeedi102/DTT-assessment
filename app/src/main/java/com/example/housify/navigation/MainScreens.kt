package com.example.housify.navigation

import com.example.housify.data.network.Constant.DETAILS_SCREEN
import com.example.housify.data.network.Constant.HOME_SCREEN

/**
 * The class for navigation in main screens.
 */
sealed class MainScreens(val route: String) {
    object Home : MainScreens(HOME_SCREEN)
    object Details : MainScreens(DETAILS_SCREEN)
}