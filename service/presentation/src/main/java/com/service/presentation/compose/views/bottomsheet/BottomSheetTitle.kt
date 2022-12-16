package com.service.presentation.compose.views.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.service.presentation.R
import com.service.presentation.compose.AppTextStyle

@Preview
@Composable
private fun PreviewBottomSheetTitle() {
    BottomSheetTitle(title = "Sample title")
}

@Composable
fun BottomSheetTitle(
    modifier: Modifier = Modifier,
    title: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier,
    ) {
        Text(
            text = title,
            style = AppTextStyle.SemiBoldPrimary.copy(fontSize = 20.sp),
        )
        Divider(
            Modifier
                .width(50.dp)
                .height(2.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(colorResource(id = R.color.divider))
        )
    }
}
