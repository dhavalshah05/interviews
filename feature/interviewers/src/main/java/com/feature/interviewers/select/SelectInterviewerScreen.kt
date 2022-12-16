package com.feature.interviewers.select

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.data.interviewers.Interviewer
import com.data.interviewers.PreviewData
import com.service.presentation.R
import com.service.presentation.compose.scroll.rememberViewInteropNestedScrollConnection
import com.service.presentation.compose.views.bottomsheet.BottomSheetContainer
import com.service.presentation.compose.views.bottomsheet.BottomSheetTitle
import com.service.presentation.compose.views.selectableitem.SelectableItem
import com.service.presentation.model.Selectable

@Preview
@Composable
private fun PreviewSelectManagerScreen() {
    SelectInterviewerScreen(
        items = PreviewData.getInterviewers().map { Selectable(item = it, selected = true) },
        onItemSelect = {}
    )
}

@Composable
internal fun SelectInterviewerScreen(
    modifier: Modifier = Modifier,
    items: List<Selectable<Interviewer>>,
    onItemSelect: (Selectable<Interviewer>) -> Unit
) {
    BottomSheetContainer(
        modifier = modifier
            .padding(top = 20.dp)
            .nestedScroll(rememberViewInteropNestedScrollConnection())
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item(key = "title") {
                BottomSheetTitle(title = stringResource(id = R.string.label_select_interviewer))
            }
            items(
                items = items,
                key = { it.item.id },
                itemContent = { item ->
                    SelectableItem(
                        isSelected = { item.selected },
                        onClick = { onItemSelect(item) },
                        content = {
                            Text("#${item.item.id}")
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(item.item.name, modifier = Modifier.weight(1F))
                        }
                    )
                }
            )
        }
    }
}
