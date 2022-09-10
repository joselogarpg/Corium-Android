package dev.joselogar.corium.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Purple800,
    primaryVariant = Cyan800,
    secondary = Orange800,
    secondaryVariant = Pink800,
    onPrimary = Grey900,
    onSecondary = Grey100,
    error = Red500,
    background = Grey900
)

private val LightColorPalette = lightColors(
    primary = Purple200,
    primaryVariant = Cyan200,
    secondary = Orange200,
    secondaryVariant = Pink200,
    onPrimary = Grey100,
    onSecondary = Grey900,
    error = Red500,
    background = Grey100
)

@Composable
fun CoriumTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}