package com.service.presentation.compose.font

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.service.presentation.R

val fontPoppins = FontFamily(
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_regular, FontWeight.Regular),
    Font(R.font.poppins_semi_bold, FontWeight.SemiBold),
)

val FontWeight.Companion.Regular: FontWeight
    get() = FontWeight(1)