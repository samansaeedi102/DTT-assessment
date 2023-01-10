package com.example.housify.navigation

import android.graphics.drawable.Icon
import android.media.Image
import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.core.graphics.drawable.IconCompat.IconType
import com.example.housify.R


sealed class BottomBarScreen(
    val route: String,
    val icon: Int
) {
    object Home: BottomBarScreen(
        route = "HOME",
        icon = R.drawable.ic_home
    )
    object About: BottomBarScreen(
        route = "ABOUT",
        icon = R.drawable.ic_info
    )

}