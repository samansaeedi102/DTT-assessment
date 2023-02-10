package com.example.housify.navigation

import com.example.housify.R
import com.example.housify.data.network.Constant.ABOUT
import com.example.housify.data.network.Constant.HOME

/**
 * The class for navigation by bottom bar.
 */
sealed class BottomBarScreen(
    val route: String,
    val icon: Int
) {
    object Home : BottomBarScreen(
        route = HOME,
        icon = R.drawable.ic_home
    )

    object About : BottomBarScreen(
        route = ABOUT,
        icon = R.drawable.ic_info
    )

}