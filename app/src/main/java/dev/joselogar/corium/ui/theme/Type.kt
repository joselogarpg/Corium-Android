package dev.joselogar.corium.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        textAlign = TextAlign.Center,
        letterSpacing = 2.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(800),
        fontSize = 28.sp
    ),
    h2 = TextStyle(
        textAlign = TextAlign.Center,
        letterSpacing = 2.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(700),
        fontSize = 26.sp
    ),
    h3 = TextStyle(
        textAlign = TextAlign.Center,
        letterSpacing = 2.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(600),
        fontSize = 24.sp
    ),
    h4 = TextStyle(
        textAlign = TextAlign.Center,
        letterSpacing = 2.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(500),
        fontSize = 22.sp
    ),
    h5 = TextStyle(
        textAlign = TextAlign.Center,
        letterSpacing = 2.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(400),
        fontSize = 20.sp
    ),
    h6 = TextStyle(
        textAlign = TextAlign.Center,
        letterSpacing = 2.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(300),
        fontSize = 18.sp
    ),
    subtitle1 = TextStyle(
        letterSpacing = 1.sp,
        fontFamily = FontFamily.Default,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        letterSpacing = 1.sp,
        fontFamily = FontFamily.Default,
        fontSize = 14.sp
    ),
    button = TextStyle(
        textAlign = TextAlign.Center,
        letterSpacing = 1.sp,
        fontFamily = FontFamily.Default,
        fontSize = 14.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)