package com.service.presentation.compose.views.useritem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.service.presentation.R
import com.service.presentation.compose.AppTextStyle

@Preview
@Composable
private fun PreviewUiUserItem() {
    UiUserItem(
        id = "#10",
        name = "User name",
        onDeleteClick = {}
    )
}

@Composable
fun UiUserItem(
    id: String,
    name: String,
    onDeleteClick: () -> Unit,
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = id,
                modifier = Modifier.padding(start = 20.dp),
                style = AppTextStyle.RegularPrimary.copy(fontSize = 18.sp)
            )
            Text(
                text = name,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .weight(1f),
                style = AppTextStyle.RegularPrimary.copy(fontSize = 18.sp)
            )
            IconButton(
                onClick = onDeleteClick,
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = "Delete icon",
                        tint = colorResource(id = R.color.primary)
                    )
                }
            )
        }
        Divider(color = colorResource(id = R.color.divider))
    }
}
