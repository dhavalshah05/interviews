package com.template.app.compose.views.interviewdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.template.app.R
import com.template.app.compose.views.texts.AppTextStyle

@Preview
@Composable
private fun PreviewInterviewDetailPrimaryContainer() {
    InterviewDetailSecondaryContainer(
        label = "Interview comments",
        value = "Here are some interview comments to look into.",
        modifier = Modifier.padding(20.dp)
    )
}

@Composable
fun InterviewDetailSecondaryContainer(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.cardBackground), RoundedCornerShape(4.dp))
            .padding(horizontal = 20.dp, vertical = 10.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = AppTextStyle.Regular,
                color = colorResource(id = R.color.primary),
                fontSize = 14.sp,
                modifier = Modifier.weight(1F),
            )
            if (value.isNotBlank()) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = "",
                    tint = colorResource(id = R.color.primary)
                )
            } else {
                Text(
                    text = stringResource(id = R.string.label_not_available),
                    style = AppTextStyle.Regular,
                    color = colorResource(id = R.color.textSecondary),
                    fontSize = 14.sp,
                )
            }
        }
        if (value.isNotBlank()) {
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = value,
                style = AppTextStyle.SemiBold,
                color = colorResource(id = R.color.textPrimary),
                fontSize = 14.sp,
                textAlign = TextAlign.Start
            )
        }
    }

}