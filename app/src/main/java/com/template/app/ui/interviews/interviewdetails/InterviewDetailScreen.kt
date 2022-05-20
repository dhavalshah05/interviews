package com.template.app.ui.interviews.interviewdetails

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
import com.template.app.R
import com.template.app.compose.views.interviewdetail.InterviewDetailPrimaryContainer
import com.template.app.compose.views.interviewdetail.InterviewDetailSecondaryContainer
import com.template.app.compose.views.interviewitem.InterviewResultStatus
import com.template.app.compose.views.texts.AppTextStyle
import com.template.app.compose.views.toolbars.ToolbarSecondary
import com.template.app.domain.interviews.models.Interview
import com.template.app.ui.PrototypeData
import com.template.app.util.date.DateUtils

@Preview
@Composable
private fun PreviewInterviewDetailScreen() {
    InterviewDetailScreen(
        interview = PrototypeData.getInterview(),
        onBackClick = {},
        onEditInterviewClick = {},
        onDeleteInterviewClick = {},
        onEditInterviewResultClick = {}
    )
}

@Composable
fun InterviewDetailScreen(
    modifier: Modifier = Modifier,
    interview: Interview,
    onBackClick: () -> Unit,
    onEditInterviewClick: () -> Unit,
    onDeleteInterviewClick: () -> Unit,
    onEditInterviewResultClick: () -> Unit,
) {
    val date = remember(interview.interviewDate) {
        DateUtils.format(inputDate = interview.interviewDate, outFormat = DateUtils.DateFormat.PRIMARY_DATE)
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
                style = AppTextStyle.SemiBold,
                color = colorResource(id = R.color.textPrimary),
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = date,
                style = AppTextStyle.Regular,
                color = colorResource(id = R.color.textSecondary),
                fontSize = 14.sp
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
            InterviewDetailPrimaryContainer(label = stringResource(id = R.string.label_interviewer), value = interview.interviewer.name)
            Spacer(modifier = Modifier.height(20.dp))
            InterviewDetailPrimaryContainer(label = stringResource(id = R.string.label_hr_manager), value = interview.manager.name)
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