package com.feature.managers.select

import androidx.compose.foundation.layout.padding
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
import com.data.managers.Manager
import com.data.managers.PreviewData
import com.service.presentation.R
import com.service.presentation.compose.scroll.rememberViewInteropNestedScrollConnection
import com.service.presentation.compose.views.bottomsheet.BottomSheetContainer
import com.service.presentation.compose.views.bottomsheet.BottomSheetTitle
import com.service.presentation.compose.views.selectableitem.SelectableItem
import com.service.presentation.model.Selectable

@Preview
@Composable
private fun PreviewSelectManagerScreen() {
    SelectManagerScreen(
        items = PreviewData.getManagers().map { Selectable(item = it, selected = false) },
        onItemSelect = {}
    )
}

@Composable
internal fun SelectManagerScreen(
    modifier: Modifier = Modifier,
    items: List<Selectable<Manager>>,
    onItemSelect: (Selectable<Manager>) -> Unit
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
                BottomSheetTitle(title = stringResource(id = R.string.label_select_manager))
            }
            items(
                items = items,
                key = { it.item.id },
                itemContent = { item ->
                    SelectableItem(
                        isSelected = { item.selected },
                        onClick = { onItemSelect(item) },
                        content = {
                            Text(item.item.name)
                        }
                    )
                }
            )
        }
    }
}