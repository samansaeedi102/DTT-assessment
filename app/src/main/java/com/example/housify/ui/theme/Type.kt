package com.example.housify.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.housify.R

val boldFont = FontFamily(
    Font(R.font.gothamssm_bold, FontWeight.Bold)
)
val mediumFont = FontFamily(
    Font(R.font.gothamssm_medium, FontWeight.Medium)
)
val bookFont = FontFamily(
    Font(R.font.gothamssm_book, FontWeight.Normal)
)
val lightFont = FontFamily(
    Font(R.font.gothamssm_light, FontWeight.Normal)
)
// Set of Material typography styles to start with
val typography = Typography(
    //Style for Title 01
    h1 = TextStyle(
        fontFamily = boldFont,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    //Style for Title 02
    h2 = TextStyle(
        fontFamily = boldFont,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    //Style for Title 03
    h3 = TextStyle(
        fontFamily = mediumFont,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    //Style for Body & Hint
    body1 = TextStyle(
        fontFamily = bookFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    //Style for input
    body2 = TextStyle(
        fontFamily = lightFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    //Style for Subtitle
    subtitle1 = TextStyle(
        fontFamily = bookFont,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    ),
    //Style for Detail
    subtitle2 = TextStyle(
        fontFamily = bookFont,
        fontWeight = FontWeight.Normal,
        fontSize = 8.sp
    )
)