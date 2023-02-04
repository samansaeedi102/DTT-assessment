package com.example.housify.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(

)

private val LightColorPalette = lightColors(
    primary = White,
    onPrimary = Medium,
    background = LightGray,
    surface = White,
    onSurface = Medium,
    secondary = Medium,
    primaryVariant = DarkGray
)

@Composable
fun HousifyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = Shapes,
        content = content
    )
}