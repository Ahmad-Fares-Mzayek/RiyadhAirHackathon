package com.example.riyadhairhackathon.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryPurple,
    secondary = LightIndigo,
    tertiary = Pink80,
    background = DeepNavyPurple,
    surface = DeepNavyPurple,
    onPrimary = WhiteBackground,
    onSecondary = WhiteBackground,
    onTertiary = WhiteBackground,
    onBackground = WhiteBackground,
    onSurface = WhiteBackground,
)

private val LightColorScheme = lightColorScheme(
    primary = DeepNavyPurple,
    secondary = LightIndigo,
    tertiary = Pink40,
    background = WhiteBackground,
    surface = WhiteBackground,
    onPrimary = WhiteBackground,
    onSecondary = WhiteBackground,
    onTertiary = WhiteBackground,
    onBackground = DarkGrayText,
    onSurface = DarkGrayText,

    /* Other default colors to override
    surfaceVariant = Color.White,
    onSurfaceVariant = DarkGrayText,
    outline = LightIndigo
    */
)

@Composable
fun RiyadhAirHackathonTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // Disable dynamic color to stick to brand
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}