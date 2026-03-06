package com.putrapebrianonurba.shakifyid.core.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.putrapebrianonurba.shakifyid.R

val Poppins = FontFamily(
    Font(R.font.poppins_light, FontWeight.Light),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold),
)

val Typography = Typography(
    // MAGNITUDE
    displaySmall = TextStyle(
        fontFamily = Poppins,
        fontSize = 18.sp
    ),

    // EARTHQUAKE INFORMATION & CITY
    bodyLarge = TextStyle(
        fontFamily = Poppins,
        fontSize = 14.sp
    ),

    // LAT LONG
    bodySmall = TextStyle(
        fontFamily = Poppins,
        fontSize = 12.sp
    )
)
