package com.feature.interviews

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.data.interviews.Interview
import com.data.interviews.PreviewData
import com.service.presentation.R
import com.service.presentation.compose.AppTextStyle
import com.service.presentation.utils.superdatetime.DateTimePattern
import com.service.presentation.utils.superdatetime.SuperDateTime

@Preview
@Composable
private fun PreviewInterviewCard() {
    InterviewCard(
        modifier = Modifier.padding(10.dp),
        interview = PreviewData.getInterview(),
        onClick = {},
        interviewResultStatusWidth = Dp.Infinity,
        onInterviewResultStatusWidthUpdate = {}
    )
}

@Composable
internal fun InterviewCard(
    modifier: Modifier = Modifier,
    interview: Interview,
    onClick: () -> Unit,
    interviewResultStatusWidth: Dp,
    onInterviewResultStatusWidthUpdate: (Int) -> Unit
) {
    val date = remember(interview.interviewDate) {
        val dateTime = SuperDateTime.toDateTime(timestamp = interview.interviewDate)
        SuperDateTime.toText(dateTime, DateTimePattern.PRIMARY)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = colorResource(id = R.color.cardBackground), shape = RoundedCornerShape(8.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = onClick
            )
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "#${interview.id}",
                style = AppTextStyle.MediumPrimary,
            )
            Text(
                text = interview.candidateName,
                style = AppTextStyle.SemiBoldPrimary.copy(fontSize = 24.sp),
            )
            Text(
                text = date,
                style = AppTextStyle.RegularSecondary.copy(fontSize = 10.sp),
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = interview.interviewerName,
                style = AppTextStyle.RegularPrimary.copy(fontSize = 12.sp),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .border(width = 1.dp, color = colorResource(id = R.color.cardBorder), shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 8.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = interview.experience,
                style = AppTextStyle.BoldPrimary.copy(fontSize = 24.sp),
            )
            Text(
                text = stringResource(id = R.string.label_years),
                style = AppTextStyle.RegularPrimary.copy(fontSize = 10.sp),
                modifier = Modifier.offset { IntOffset(y = -12, x = 0) }
            )
            InterviewResultStatus(
                interviewResult = interview.result,
                fontSize = 10F,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .onGloballyPositioned {
                        val width = it.size.width
                        onInterviewResultStatusWidthUpdate(width)
                    },
                width = interviewResultStatusWidth
            )
        }
    }
}
