package com.template.app.ui.interviews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.template.app.R
import com.template.app.compose.views.interviewitem.InterviewCard
import com.template.app.compose.views.textfields.SearchTextField
import com.template.app.compose.views.toolbars.ToolbarPrimary
import com.template.app.domain.interviews.models.Interview
import com.template.app.ui.PrototypeData
import com.template.app.util.display.DisplayMetrics

@Preview
@Composable
private fun PreviewInterviewsScreen() {
    InterviewsScreen(
        onSettingsClick = {},
        onSearchTextChange = {},
        onInterviewCardClick = {},
        interviews = PrototypeData.getInterviews()
    )
}

@Composable
fun InterviewsScreen(
    modifier: Modifier = Modifier,
    interviews: List<Interview>,
    onSettingsClick: () -> Unit,
    onSearchTextChange: (String) -> Unit,
    onInterviewCardClick: (Interview) -> Unit,
) {

    val focusManager = LocalFocusManager.current

    val searchText = remember {
        mutableStateOf("")
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        val width = remember {
            mutableStateOf(0)
        }

        Column {
            ToolbarPrimary(
                title = stringResource(id = R.string.screen_title_interviews),
                onSettingsClick = onSettingsClick
            )

            SearchTextField(
                value = searchText.value,
                onValueChange = {
                    searchText.value = it
                    onSearchTextChange.invoke(it)
                },
                onDoneClick = {
                    focusManager.clearFocus()
                },
                onClearClick = {
                    searchText.value = ""
                    onSearchTextChange.invoke("")
                },
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                contentPadding = PaddingValues(
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 20.dp
                ),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                content = {
                    items(interviews) { interview ->
                        InterviewCard(
                            interview = interview,
                            onClick = { onInterviewCardClick.invoke(interview) },
                            interviewResultStatusWidth = if (width.value == 0) Dp.Infinity else width.value.dp,
                            onInterviewResultStatusWidthUpdate = {
                                val newWidth = DisplayMetrics.convertPixelsToDp(it.toFloat()).toInt()
                                if (newWidth <= width.value) return@InterviewCard
                                width.value = newWidth
                            }
                        )
                    }
                }
            )
        }
    }
}