package com.orion.templete.presentation.ui.theme

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel

const val DEFAULT_PADDING = 44
private val DarkColorScheme = darkColorScheme(
    primary = DarkColorPrimary,                           // Primary color (green)
    onPrimary = DarkColorOnPrimary,                       // Color of text and icons displayed on top of the primary color
    primaryContainer = DarkColorPrimaryVariant,           // Variant of primary color
    onPrimaryContainer = DarkColorOnPrimaryVariant,
    inversePrimary = DarkColorOnPrimary,                  // Color of text and icons displayed on top of the primary color (inverse)
    secondary = DarkColorSecondary,                       // Secondary color (blue)
    onSecondary = DarkColorOnSecondary,                     // Color of text and icons displayed on top of the secondary color
//    secondaryContainer = DarkColorSecondaryVariant,       // Variant of secondary color
//    onSecondaryContainer = DarkColorOnSecondary,          // Color of text and icons displayed on top of the secondary color container
    background = DarkColorBackground,                     // Background color
    onBackground = DarkColorOnBackground,                // Color of text and icons displayed on top of the background color
//    surface = DarkColorSurface,                           // Surface color of components like cards, buttons
//    onSurface = DarkColorOnSurface,                       // Color of text and icons displayed on top of the surface color
//    surfaceTint = DarkColorPrimary,                        // Surface color tint
//    error = DarkColorError,                               // Color of error texts
//    onError = DarkColorOnError,                           // Color of text and icons displayed on top of the error color
//    errorContainer = DarkColorErrorContainer,             // Error color container
//    onErrorContainer = DarkColorOnErrorContainer,         // Color of text and icons displayed on top of the error color container
//    outline = DarkColorInputBorder,                       // Color for input field borders
//    scrim = DarkColorScrim                                // Scrim color
)


private val LightColorScheme = lightColorScheme(
    primary = ColorPrimary,                                // Primary color (green)
    onPrimary = ColorOnPrimary,                            // Color of text and icons displayed on top of the primary color
    primaryContainer = ColorPrimaryVariant,                // Variant of primary color
    onPrimaryContainer = ColorOnPrimaryVariant,
    inversePrimary = ColorOnPrimary,                       // Color of text and icons displayed on top of the primary color (inverse)
    secondary = ColorSecondary,                            // Secondary color (blue)
    onSecondary = ColorOnSecondary,                        // Color of text and icons displayed on top of the secondary color
//    secondaryContainer = ColorSecondaryVariant,            // Variant of secondary color
//    onSecondaryContainer = ColorOnSecondary,               // Color of text and icons displayed on top of the secondary color container
//    tertiary = ColorTextTertiaryDark,                      // Tertiary text color (dark gray)
//    onTertiary = ColorTextTertiaryLight,                   // Color of text and icons displayed on top of the tertiary color
//    tertiaryContainer = ColorTextTertiaryDark,             // Tertiary color container
//    onTertiaryContainer = ColorTextTertiaryLight,          // Color of text and icons displayed on top of the tertiary color container
//    background = ColorBackground,                          // Background color
//    onBackground = ColorOnBackground,                      // Color of text and icons displayed on top of the background color
//    surface = ColorSurface,                                // Surface color of components like cards, buttons
//    onSurface = ColorOnSurface,                            // Color of text and icons displayed on top of the surface color
//    surfaceVariant = ColorSurfaceLight,                    // Variant of surface color
//    onSurfaceVariant = ColorOnSurfaceLight,                // Color of text and icons displayed on top of the surface color variant
//    surfaceTint = ColorPrimary,                            // Surface color tint
//    inverseSurface = ColorOnSurfaceLight,                  // Color of text and icons displayed on top of the surface color (inverse)
//    inverseOnSurface = ColorOnSurfaceLight,                // Color of text and icons displayed on top of the surface color (inverse)
//    error = ColorError,                                    // Color of error texts
//    onError = ColorOnError,                                // Color of text and icons displayed on top of the error color
//    errorContainer = ColorError,                           // Error color container
//    onErrorContainer = ColorOnError,                       // Color of text and icons displayed on top of the error color container
//    outline = ColorInputBorder,                            // Color for input field borders
//    outlineVariant = ColorInputBorderLight,                 // Variant of input field border color
//    scrim = ColorSurface                                    // Scrim color

)

@Composable
fun TempleteTheme(
    themeViewModel: ThemeViewModel = hiltViewModel(),
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = themeViewModel.dynamicColor.collectAsState().value,
    content: @Composable () -> Unit
) {
    val themeState by themeViewModel.themeStateHolder.collectAsState()
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (themeState.isDarkMode) dynamicDarkColorScheme(context) else dynamicLightColorScheme(
                context
            )
        }

        themeState.isDarkMode -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primaryContainer.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}