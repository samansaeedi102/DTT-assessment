package com.example.housify.utils

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val default: Dp = 0.dp,
    val smallElevation: Dp = 4.dp,
    val xxixSmall: Dp = 5.dp,
    val xxxSmall: Dp = 10.dp,
    val xxSmall: Dp = 13.dp,
    val extraSmall: Dp = 15.dp,
    val small: Dp = 16.dp,
    val medium: Dp = 18.dp,
    val large: Dp = 20.dp,
    val extraLarge: Dp = 40.dp,
    val xxLarge: Dp = 150.dp,
    val xxxLarge: Dp = 250.dp,
    val xxixLarge: Dp = 700.dp,
    val startPadding: Dp = 30.dp,
    val topPadding: Dp = 40.dp,
    val endPadding: Dp = 30.dp,
    val bottomPadding: Dp = 50.dp,
    val cardRoundedCorner: Dp = 20.dp,
    val minusOffset: Dp = (-15).dp,
    val bannerWidth: Dp = 120.dp,
    val floatingButtonMinusWidth: Dp = (-165).dp,
    val floatingButtonMinusHeight: Dp = (-720).dp,
    val houseSizeOnCard: Dp = 90.dp,
    val emptySearchImage: Dp = 350.dp

)


val LocalSpacing = compositionLocalOf { Spacing() }

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current