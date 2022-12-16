package com.service.presentation.compose.views.selectableitem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.service.presentation.R
import com.service.presentation.compose.AppTextStyle
import com.service.presentation.compose.extensions.clickableWithRipple

@Preview
@Composable
private fun PreviewSelectableItem() {
    SelectableItem(
        isSelected = { true },
        onClick = {},
        content = {
            Text(
                text = "Some Title".plus(" "),
                style = AppTextStyle.RegularPrimary,
                modifier = Modifier.weight(1F)
            )
        }
    )
}

@Composable
fun SelectableItem(
    modifier: Modifier = Modifier,
    isSelected: () -> Boolean,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .clickableWithRipple(onClick)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier.weight(1F)) {
                content()
            }

            if (isSelected()) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_tick),
                    contentDescription = "",
                    tint = colorResource(id = R.color.primary)
                )
            }
        }
        Divider(Modifier.fillMaxWidth(), thickness = 1.dp, color = colorResource(id = R.color.divider))
    }
}
