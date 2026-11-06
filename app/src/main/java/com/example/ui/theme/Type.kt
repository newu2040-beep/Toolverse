package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.R

val JakartaFontFamily = FontFamily(
    Font(R.font.plus_jakarta_sans, FontWeight.Normal),
    Font(R.font.plus_jakarta_sans, FontWeight.Medium),
    Font(R.font.plus_jakarta_sans, FontWeight.SemiBold),
    Font(R.font.plus_jakarta_sans, FontWeight.Bold)
)

val InterFontFamily = FontFamily(
    Font(R.font.inter, FontWeight.Normal),
    Font(R.font.inter, FontWeight.Medium),
    Font(R.font.inter, FontWeight.SemiBold),
    Font(R.font.inter, FontWeight.Bold)
)

val PoppinsFontFamily = FontFamily(
    Font(R.font.poppins, FontWeight.Normal),
    Font(R.font.poppins, FontWeight.Medium),
    Font(R.font.poppins, FontWeight.SemiBold),
    Font(R.font.poppins, FontWeight.Bold)
)

val NunitoFontFamily = FontFamily(
    Font(R.font.nunito, FontWeight.Normal),
    Font(R.font.nunito, FontWeight.Medium),
    Font(R.font.nunito, FontWeight.SemiBold),
    Font(R.font.nunito, FontWeight.Bold)
)

val SpaceGroteskFontFamily = FontFamily(
    Font(R.font.space_grotesk, FontWeight.Normal),
    Font(R.font.space_grotesk, FontWeight.Medium),
    Font(R.font.space_grotesk, FontWeight.SemiBold),
    Font(R.font.space_grotesk, FontWeight.Bold)
)

val LoraFontFamily = FontFamily(
    Font(R.font.lora, FontWeight.Normal),
    Font(R.font.lora, FontWeight.Medium),
    Font(R.font.lora, FontWeight.SemiBold),
    Font(R.font.lora, FontWeight.Bold)
)

val JetBrainsMonoFontFamily = FontFamily(
    Font(R.font.jetbrains_mono, FontWeight.Normal),
    Font(R.font.jetbrains_mono, FontWeight.Medium),
    Font(R.font.jetbrains_mono, FontWeight.Bold)
)

data class FontChoiceOption(
    val key: String,
    val label: String,
    val sampleSubtitle: String
)

val AvailableFontChoices: List<FontChoiceOption> = listOf(
    FontChoiceOption("SYSTEM", "System Default", "Native Android device font"),
    FontChoiceOption("JAKARTA", "Plus Jakarta Sans", "Modern geometric display & UI"),
    FontChoiceOption("INTER", "Inter Sans", "High-legibility dense interface"),
    FontChoiceOption("POPPINS", "Poppins", "Balanced circular geometric"),
    FontChoiceOption("NUNITO", "Nunito Rounded", "Soft friendly rounded terminals"),
    FontChoiceOption("SPACE", "Space Grotesk", "Technical engineered sans"),
    FontChoiceOption("LORA", "Lora Serif", "Editorial classic book serif"),
    FontChoiceOption("MONO", "JetBrains Mono", "Developer fixed-width code")
)

fun buildToolverseTypography(fontChoice: String = "JAKARTA"): Typography {
    val selectedFamily = when (fontChoice) {
        "SYSTEM" -> FontFamily.Default
        "INTER" -> InterFontFamily
        "POPPINS" -> PoppinsFontFamily
        "NUNITO" -> NunitoFontFamily
        "SPACE" -> SpaceGroteskFontFamily
        "LORA" -> LoraFontFamily
        "MONO" -> JetBrainsMonoFontFamily
        else -> JakartaFontFamily
    }

    return Typography(
        displayLarge = TextStyle(
            fontFamily = selectedFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            lineHeight = 38.sp,
            letterSpacing = (-0.4).sp
        ),
        displayMedium = TextStyle(
            fontFamily = selectedFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            lineHeight = 32.sp,
            letterSpacing = (-0.3).sp
        ),
        headlineLarge = TextStyle(
            fontFamily = selectedFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 23.sp,
            lineHeight = 29.sp,
            letterSpacing = (-0.2).sp
        ),
        headlineMedium = TextStyle(
            fontFamily = selectedFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 19.sp,
            lineHeight = 25.sp
        ),
        titleLarge = TextStyle(
            fontFamily = selectedFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            lineHeight = 23.sp
        ),
        titleMedium = TextStyle(
            fontFamily = selectedFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.5.sp,
            lineHeight = 20.sp
        ),
        titleSmall = TextStyle(
            fontFamily = selectedFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            lineHeight = 18.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = selectedFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            lineHeight = 22.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = selectedFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
            lineHeight = 19.sp
        ),
        bodySmall = TextStyle(
            fontFamily = selectedFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp
        ),
        labelLarge = TextStyle(
            fontFamily = selectedFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.5.sp,
            lineHeight = 18.sp
        ),
        labelMedium = TextStyle(
            fontFamily = selectedFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 16.sp
        ),
        labelSmall = TextStyle(
            fontFamily = selectedFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 14.sp
        )
    )
}

val Typography = buildToolverseTypography("JAKARTA")
