package com.template.app.compose.views.texts

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.template.app.R
import com.template.app.compose.Regular
import com.template.app.compose.fontPoppins

object AppTextStyle {

    @OptIn(ExperimentalTextApi::class)
    val RegularPrimary: TextStyle
        @Composable get() = TextStyle.Default.copy(
            fontSize = 14.sp,
            fontFamily = fontPoppins,
            fontWeight = FontWeight.Regular,
            platformStyle = PlatformTextStyle(includeFontPadding = false),
            color = colorResource(id = R.color.textPrimary)
        )

    val RegularSecondary: TextStyle
        @Composable get() = RegularPrimary.copy(color = colorResource(id = R.color.textSecondary))

    val SemiBoldPrimary: TextStyle
        @Composable get() = RegularPrimary.copy(fontWeight = FontWeight.SemiBold)

    val MediumPrimary: TextStyle
        @Composable get() = RegularPrimary.copy(fontWeight = FontWeight.Medium)

    val BoldPrimary: TextStyle
        @Composable get() = RegularPrimary.copy(fontWeight = FontWeight.Bold)
}