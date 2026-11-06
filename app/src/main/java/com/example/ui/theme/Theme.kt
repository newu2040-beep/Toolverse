package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val LightColorScheme = lightColorScheme(
    primary = ForestGreenPrimary,
    onPrimary = Color.White,
    primaryContainer = ForestMintContainer,
    onPrimaryContainer = OnForestMintContainer,
    secondary = ForestGreenLight,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFD9F0E5),
    onSecondaryContainer = ForestGreenDark,
    tertiary = CatCvAccent,
    onTertiary = Color.White,
    background = WarmCreamBackground,
    onBackground = InkPrimary,
    surface = PureCardSurface,
    onSurface = InkPrimary,
    surfaceVariant = WarmCreamSurfaceVariant,
    onSurfaceVariant = InkSecondary,
    outline = SubtleCardBorder,
    outlineVariant = Color(0xFFEDE8DD),
    error = Color(0xFFDC2626),
    onError = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = EmeraldPrimaryDark,
    onPrimary = Color(0xFF042619),
    primaryContainer = EmeraldContainerDark,
    onPrimaryContainer = Color(0xFFD1FAE5),
    secondary = Color(0xFF6EE7B7),
    onSecondary = Color(0xFF062E1E),
    secondaryContainer = Color(0xFF143D2C),
    onSecondaryContainer = Color(0xFFA7F3D0),
    tertiary = Color(0xFF60A5FA),
    onTertiary = Color(0xFF0A192F),
    background = DarkForestBackground,
    onBackground = Color(0xFFF1F5F3),
    surface = DarkForestSurface,
    onSurface = Color(0xFFF1F5F3),
    surfaceVariant = DarkForestSurfaceVariant,
    onSurfaceVariant = Color(0xFFA8B9B0),
    outline = DarkCardBorder,
    outlineVariant = Color(0xFF1E3128),
    error = Color(0xFFF87171),
    onError = Color(0xFF450A0A)
)

private val PastelMintColorScheme = lightColorScheme(
    primary = PastelMintPrimary,
    onPrimary = Color.White,
    primaryContainer = PastelMintContainer,
    onPrimaryContainer = Color(0xFF073B25),
    secondary = Color(0xFF289668),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFD7F5E5),
    onSecondaryContainer = Color(0xFF0B422B),
    tertiary = Color(0xFF0284C7),
    onTertiary = Color.White,
    background = PastelMintBg,
    onBackground = Color(0xFF10241B),
    surface = PastelMintSurface,
    onSurface = Color(0xFF10241B),
    surfaceVariant = PastelMintSurfaceVariant,
    onSurfaceVariant = Color(0xFF3E5E4F),
    outline = PastelMintOutline,
    outlineVariant = Color(0xFFD8EFE3),
    error = Color(0xFFDC2626),
    onError = Color.White
)

private val PastelLavenderColorScheme = lightColorScheme(
    primary = PastelLavenderPrimary,
    onPrimary = Color.White,
    primaryContainer = PastelLavenderContainer,
    onPrimaryContainer = Color(0xFF24145E),
    secondary = Color(0xFF7C3AED),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFEDE9FE),
    onSecondaryContainer = Color(0xFF2E1065),
    tertiary = Color(0xFFDB2777),
    onTertiary = Color.White,
    background = PastelLavenderBg,
    onBackground = Color(0xFF1C1635),
    surface = PastelLavenderSurface,
    onSurface = Color(0xFF1C1635),
    surfaceVariant = PastelLavenderSurfaceVariant,
    onSurfaceVariant = Color(0xFF52477A),
    outline = PastelLavenderOutline,
    outlineVariant = Color(0xFFE4DDFC),
    error = Color(0xFFDC2626),
    onError = Color.White
)

private val PastelPeachColorScheme = lightColorScheme(
    primary = PastelPeachPrimary,
    onPrimary = Color.White,
    primaryContainer = PastelPeachContainer,
    onPrimaryContainer = Color(0xFF4A1808),
    secondary = Color(0xFFD97706),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFEF3C7),
    onSecondaryContainer = Color(0xFF451A03),
    tertiary = Color(0xFF0D9488),
    onTertiary = Color.White,
    background = PastelPeachBg,
    onBackground = Color(0xFF2B1810),
    surface = PastelPeachSurface,
    onSurface = Color(0xFF2B1810),
    surfaceVariant = PastelPeachSurfaceVariant,
    onSurfaceVariant = Color(0xFF6E4B3E),
    outline = PastelPeachOutline,
    outlineVariant = Color(0xFFFCE2D7),
    error = Color(0xFFDC2626),
    onError = Color.White
)

private val PastelSkyColorScheme = lightColorScheme(
    primary = PastelSkyPrimary,
    onPrimary = Color.White,
    primaryContainer = PastelSkyContainer,
    onPrimaryContainer = Color(0xFF082F4E),
    secondary = Color(0xFF0284C7),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE0F2FE),
    onSecondaryContainer = Color(0xFF0C4A6E),
    tertiary = Color(0xFF4F46E5),
    onTertiary = Color.White,
    background = PastelSkyBg,
    onBackground = Color(0xFF0F2133),
    surface = PastelSkySurface,
    onSurface = Color(0xFF0F2133),
    surfaceVariant = PastelSkySurfaceVariant,
    onSurfaceVariant = Color(0xFF415E78),
    outline = PastelSkyOutline,
    outlineVariant = Color(0xFFD6ECFA),
    error = Color(0xFFDC2626),
    onError = Color.White
)

private val PastelRoseColorScheme = lightColorScheme(
    primary = PastelRosePrimary,
    onPrimary = Color.White,
    primaryContainer = PastelRoseContainer,
    onPrimaryContainer = Color(0xFF4A0D26),
    secondary = Color(0xFFDB2777),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFCE7F3),
    onSecondaryContainer = Color(0xFF500724),
    tertiary = Color(0xFF7C3AED),
    onTertiary = Color.White,
    background = PastelRoseBg,
    onBackground = Color(0xFF29121B),
    surface = PastelRoseSurface,
    onSurface = Color(0xFF29121B),
    surfaceVariant = PastelRoseSurfaceVariant,
    onSurfaceVariant = Color(0xFF6E4253),
    outline = PastelRoseOutline,
    outlineVariant = Color(0xFFFADCE7),
    error = Color(0xFFDC2626),
    onError = Color.White
)

private val PastelLemonColorScheme = lightColorScheme(
    primary = PastelLemonPrimary,
    onPrimary = Color.White,
    primaryContainer = PastelLemonContainer,
    onPrimaryContainer = Color(0xFF3B2804),
    secondary = Color(0xFF0F5338),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE3F5EC),
    onSecondaryContainer = Color(0xFF093824),
    tertiary = Color(0xFFB45309),
    onTertiary = Color.White,
    background = PastelLemonBg,
    onBackground = Color(0xFF241D0B),
    surface = PastelLemonSurface,
    onSurface = Color(0xFF241D0B),
    surfaceVariant = PastelLemonSurfaceVariant,
    onSurfaceVariant = Color(0xFF635432),
    outline = PastelLemonOutline,
    outlineVariant = Color(0xFFF4EBC9),
    error = Color(0xFFDC2626),
    onError = Color.White
)

data class ThemePaletteOption(
    val key: String,
    val label: String,
    val previewBg: Color,
    val previewPrimary: Color
)

val AvailableThemePalettes: List<ThemePaletteOption> = listOf(
    ThemePaletteOption("SYSTEM", "System Auto", WarmCreamBackground, ForestGreenPrimary),
    ThemePaletteOption("LIGHT", "Warm Cream", WarmCreamBackground, ForestGreenPrimary),
    ThemePaletteOption("DARK", "Forest Dark", DarkForestBackground, EmeraldPrimaryDark),
    ThemePaletteOption("PASTEL_MINT", "Pastel Mint", PastelMintBg, PastelMintPrimary),
    ThemePaletteOption("PASTEL_LAVENDER", "Pastel Lavender", PastelLavenderBg, PastelLavenderPrimary),
    ThemePaletteOption("PASTEL_PEACH", "Pastel Peach", PastelPeachBg, PastelPeachPrimary),
    ThemePaletteOption("PASTEL_SKY", "Pastel Sky", PastelSkyBg, PastelSkyPrimary),
    ThemePaletteOption("PASTEL_ROSE", "Pastel Rose", PastelRoseBg, PastelRosePrimary),
    ThemePaletteOption("PASTEL_LEMON", "Pastel Butter", PastelLemonBg, PastelLemonPrimary)
)

val LocalCompactMode = androidx.compose.runtime.compositionLocalOf { false }

val ToolverseShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(22.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

val CompactToolverseShapes = Shapes(
    extraSmall = RoundedCornerShape(6.dp),
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(20.dp)
)

@Composable
fun resolveColorScheme(themeMode: String): ColorScheme {
    val systemDark = isSystemInDarkTheme()
    return when (themeMode) {
        "LIGHT" -> LightColorScheme
        "DARK" -> DarkColorScheme
        "PASTEL_MINT" -> PastelMintColorScheme
        "PASTEL_LAVENDER" -> PastelLavenderColorScheme
        "PASTEL_PEACH" -> PastelPeachColorScheme
        "PASTEL_SKY" -> PastelSkyColorScheme
        "PASTEL_ROSE" -> PastelRoseColorScheme
        "PASTEL_LEMON" -> PastelLemonColorScheme
        else -> if (systemDark) DarkColorScheme else LightColorScheme
    }
}

@Composable
fun ToolverseTheme(
    themeMode: String = "SYSTEM",
    fontChoice: String = "JAKARTA",
    compactMode: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = resolveColorScheme(themeMode)
    val typography = buildToolverseTypography(fontChoice)
    val shapes = if (compactMode) CompactToolverseShapes else ToolverseShapes

    androidx.compose.runtime.CompositionLocalProvider(
        LocalCompactMode provides compactMode
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            shapes = shapes,
            content = content
        )
    }
}

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    ToolverseTheme(
        themeMode = if (darkTheme) "DARK" else "LIGHT",
        fontChoice = "JAKARTA",
        compactMode = false,
        content = content
    )
}

