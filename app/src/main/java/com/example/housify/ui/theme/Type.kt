package com.example.housify.ui.theme

import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.housify.R

val BoldFont = FontFamily(
    Font(R.font.gothamssm_bold, FontWeight.Bold)
)
val MediumFont = FontFamily(
    Font(R.font.gothamssm_medium, FontWeight.Medium)
)
val BookFont = FontFamily(
    Font(R.font.gothamssm_book, FontWeight.Normal)
)
val LightFont = FontFamily(
    Font(R.font.gothamssm_light, FontWeight.Normal)
)
// Set of Material typography styles to start with
val Typography = Typography(
    //Style for Title 01
    h1 = TextStyle(
        fontFamily = BoldFont,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    //Style for Title 02
    h2 = TextStyle(
        fontFamily = BoldFont,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    //Style for Title 03
    h3 = TextStyle(
        fontFamily = MediumFont,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    //Style for Body & Hint
    body1 = TextStyle(
        fontFamily = BookFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    //Style for input
    body2 = TextStyle(
        fontFamily = LightFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    //Style for Subtitle
    subtitle1 = TextStyle(
        fontFamily = BookFont,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    ),
    //Style for Detail
    subtitle2 = TextStyle(
        fontFamily = BookFont,
        fontWeight = FontWeight.Normal,
        fontSize = 8.sp
    )
)