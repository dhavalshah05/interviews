package com.feature.interviews.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.data.interviews.Interview
import com.data.interviews.PreviewData
import com.feature.interviews.InterviewResultStatus
import com.service.presentation.R
import com.service.presentation.compose.AppTextStyle
import com.service.presentation.compose.views.toolbar.ToolbarSecondary
import com.service.presentation.utils.superdatetime.DateTimePattern
import com.service.presentation.utils.superdatetime.SuperDateTime

@Preview
@Composable
private fun PreviewInterviewDetailScreen() {
    InterviewDetailScreen(
        interview = PreviewData.getInterview(),
        onBackClick = {},
        onEditInterviewClick = {},
        onDeleteInterviewClick = {},
        onEditInterviewResultClick = {}
    )
}

@Composable
internal fun InterviewDetailScreen(
    modifier: Modifier = Modifier,
    interview: Interview,
    onBackClick: () -> Unit,
    onEditInterviewClick: () -> Unit,
    onDeleteInterviewClick: () -> Unit,
    onEditInterviewResultClick: () -> Unit,
) {
    val date = remember(interview.interviewDate) {
        val dateTime = SuperDateTime.toDateTime(timestamp = interview.interviewDate)
        SuperDateTime.toText(dateTime, DateTimePattern.PRIMARY)
    }

    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        ToolbarSecondary(
            title = "",
            onBackClick = onBackClick,
            elevation = 0.dp,
            actions = {
                IconButton(onClick = onEditInterviewClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_edit),
                        contentDescription = "",
                        tint = colorResource(id = R.color.textPrimary)
                    )
                }
                IconButton(onClick = onDeleteInterviewClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = "",
                        tint = colorResource(id = R.color.textPrimary)
                    )
                }
            }
        )

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = interview.candidateName,
                style = AppTextStyle.SemiBoldPrimary.copy(fontSize = 30.sp),
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = date,
                style = AppTextStyle.RegularSecondary,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                InterviewResultStatus(interviewResult = interview.result, fontSize = 14F)
                IconButton(onClick = onEditInterviewResultClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_edit),
                        contentDescription = "",
                        tint = colorResource(id = R.color.textPrimary)
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            InterviewDetailPrimaryContainer(label = stringResource(id = R.string.label_experience_years), value = interview.experience)
            Spacer(modifier = Modifier.height(20.dp))
            InterviewDetailPrimaryContainer(label = stringResource(id = R.string.label_interviewer), value = interview.interviewerName)
            Spacer(modifier = Modifier.height(20.dp))
            InterviewDetailPrimaryContainer(label = stringResource(id = R.string.label_hr_manager), value = interview.managerName)
            Spacer(modifier = Modifier.height(20.dp))
            InterviewDetailSecondaryContainer(label = stringResource(id = R.string.label_interview_comments), value = interview.interviewComments)
            Spacer(modifier = Modifier.height(20.dp))
            InterviewDetailSecondaryContainer(label = stringResource(id = R.string.label_practical_comments), value = interview.practicalComments)
            Spacer(modifier = Modifier.height(20.dp))
            InterviewDetailSecondaryContainer(label = stringResource(id = R.string.label_practical_link), value = interview.practicalLink)
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}