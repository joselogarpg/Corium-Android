package dev.joselogar.corium.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

val Typography = Typography(
    h1 = TextStyle(
        textAlign = TextAlign.Center,
        letterSpacing = 2.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(700),
        fontSize = 34.sp
    ),
    h2 = TextStyle(
        textAlign = TextAlign.Center,
        letterSpacing = 2.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(700),
        fontSize = 31.sp
    ),
    h3 = TextStyle(
        textAlign = TextAlign.Center,
        letterSpacing = 2.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(600),
        fontSize = 28.sp
    ),
    h4 = TextStyle(
        textAlign = TextAlign.Center,
        letterSpacing = 2.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(600),
        fontSize = 25.sp
    ),
    h5 = TextStyle(
        textAlign = TextAlign.Center,
        letterSpacing = 2.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(500),
        fontSize = 22.sp
    ),
    h6 = TextStyle(
        textAlign = TextAlign.Center,
        letterSpacing = 2.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(500),
        fontSize = 19.sp
    ),
    subtitle1 = TextStyle(
        letterSpacing = 1.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(500),
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        letterSpacing = 1.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(400),
        fontSize = 16.sp
    ),
    body1 = TextStyle(
        letterSpacing = 1.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(500),
        fontSize = 14.sp
    ),
    body2 = TextStyle(
        letterSpacing = 1.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(400),
        fontSize = 14.sp
    ),
    button = TextStyle(
        textAlign = TextAlign.Center,
        letterSpacing = 1.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(500),
        fontSize = 16.sp
    ),
    /* Other default text styles to override
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)