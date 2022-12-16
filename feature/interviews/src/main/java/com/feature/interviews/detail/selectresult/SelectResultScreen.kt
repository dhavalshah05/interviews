package com.feature.interviews.detail.selectresult

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.data.interviews.InterviewResult
import com.service.presentation.R
import com.service.presentation.compose.AppTextStyle
import com.service.presentation.compose.views.bottomsheet.BottomSheetContainer
import com.service.presentation.compose.views.bottomsheet.BottomSheetTitle
import com.service.presentation.compose.views.selectableitem.SelectableItem
import com.service.presentation.model.Selectable

@Preview
@Composable
private fun PreviewSelectResultScreen() {
    SelectResultScreen(
        items = InterviewResult.values().toList().map { Selectable(it, false) },
        onItemSelect = {},
    )
}

@Composable
internal fun SelectResultScreen(
    items: List<Selectable<InterviewResult>>,
    onItemSelect: (Selectable<InterviewResult>) -> Unit
) {
    BottomSheetContainer(
        modifier = Modifier.padding(top = 20.dp)
    ) {
        BottomSheetTitle(title = stringResource(id = R.string.label_select_result))

        for (item in items) {
            SelectableItem(
                isSelected = { item.selected },
                onClick = { onItemSelect.invoke(item) },
                content = {
                    Text(text = item.item.name, style = AppTextStyle.RegularPrimary)
                }
            )
        }
    }
}
