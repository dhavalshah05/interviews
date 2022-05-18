package com.template.app.ui.interviewers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.template.app.R
import com.template.app.compose.views.buttons.ButtonPrimary
import com.template.app.compose.views.toolbars.ToolbarSecondary
import com.template.app.compose.views.useritem.UiUserItem
import com.template.app.domain.interviewers.models.Interviewer
import com.template.app.ui.PrototypeData
import com.template.app.util.display.DisplayMetrics

@Preview
@Composable
private fun PreviewInterviewersScreen() {
    InterviewersScreen(
        interviewers = PrototypeData.getInterviewers(),
        onDeleteClick = {},
        onBackClick = {},
        onAddClick = {}
    )
}

@Composable
fun InterviewersScreen(
    interviewers: List<Interviewer>,
    onBackClick: () -> Unit,
    onDeleteClick: (Interviewer) -> Unit,
    onAddClick: () -> Unit,
) {

    val lazyColumnPaddingBottom = remember {
        mutableStateOf(0.dp)
    }

    Box(
        modifier = Modifier.background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ToolbarSecondary(
                title = stringResource(id = R.string.screen_title_interviewers),
                onBackClick = onBackClick,
            )
            LazyColumn(
                content = {
                    items(
                        items = interviewers,
                        key = { interviewer -> interviewer.id }
                    ) { interviewer ->
                        UiUserItem(
                            id = "#${interviewer.id}",
                            name = interviewer.name,
                            onDeleteClick = { onDeleteClick.invoke(interviewer) }
                        )
                    }
                },
                contentPadding = PaddingValues(bottom = lazyColumnPaddingBottom.value)
            )
        }
        ButtonPrimary(
            text = stringResource(id = R.string.button_add_new),
            onClick = onAddClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
                .onGloballyPositioned { layoutCoordinates ->
                    val buttonHeight = layoutCoordinates.size.height.toFloat()
                    val paddingVertical = DisplayMetrics.convertDpToPixel(40f)
                    val paddingInDp = DisplayMetrics
                        .convertPixelsToDp(buttonHeight + paddingVertical)
                        .toInt()
                    lazyColumnPaddingBottom.value = paddingInDp.dp
                },
        )
    }
}