package com.feature.interviews.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.service.presentation.R
import com.service.presentation.compose.AppTextStyle

@Preview
@Composable
private fun PreviewInterviewDetailPrimaryContainer() {
    InterviewDetailPrimaryContainer(
        label = "Interviewer",
        value = "John Doe",
        modifier = Modifier.padding(20.dp)
    )
}

@Composable
internal fun InterviewDetailPrimaryContainer(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
) {
    Row(
        modifier = modifier
            .heightIn(min = 50.dp)
            .fillMaxWidth()
            .background(colorResource(id = R.color.cardBackground), RoundedCornerShape(4.dp))
            .padding(horizontal = 20.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = AppTextStyle.RegularPrimary,
        )
        Text(
            text = value,
            style = AppTextStyle.SemiBoldPrimary.copy(
                fontSize = 18.sp,
                textAlign = TextAlign.End
            ),
            modifier = Modifier.weight(1F),
        )
    }
}
