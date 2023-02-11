package com.example.housify.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.housify.utils.LocalSpacing
import com.example.housify.utils.Spacing

private val darkColorPalette = darkColors(
    primaryVariant = strong
)

private val lightColorPalette = lightColors(
    primary = white,
    onPrimary = medium,
    background = lightGray,
    surface = white,
    onSurface = medium,
    secondary = medium,
    primaryVariant = darkGray
)

@Composable
fun HousifyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        darkColorPalette
    } else {
        lightColorPalette
    }
    CompositionLocalProvider(LocalSpacing provides Spacing()) {
        MaterialTheme(
            colors = colors,
            typography = typography,
            shapes = shapes,
            content = content
        )
    }
}