package com.template.app.compose.views.texts

import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.template.app.compose.Regular
import com.template.app.compose.fontPoppins

object AppTextStyle {

    @OptIn(ExperimentalTextApi::class)
    val Regular = TextStyle.Default.copy(
        fontSize = 14.sp,
        fontFamily = fontPoppins,
        fontWeight = FontWeight.Regular,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

}