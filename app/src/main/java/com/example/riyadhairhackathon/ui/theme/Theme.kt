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
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

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

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = DeepNavyPurple.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false // Always dark background, so light icons
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}