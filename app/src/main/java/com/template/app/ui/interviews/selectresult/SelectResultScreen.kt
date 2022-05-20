package com.template.app.ui.interviews.selectresult

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.template.app.R
import com.template.app.compose.views.bottomsheet.BottomSheetContainer
import com.template.app.compose.views.bottomsheet.BottomSheetTitle
import com.template.app.compose.views.selectableitem.SelectableItem
import com.template.app.compose.views.texts.AppTextStyle
import com.template.app.domain.interviews.models.InterviewResult
import com.template.app.ui.common.models.Selectable

@Preview
@Composable
private fun PreviewSelectResultScreen() {
    SelectResultScreen(
        items = InterviewResult.values().toList().map { Selectable(it, false) },
        onItemSelect = {},
    )
}

@Composable
fun SelectResultScreen(
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