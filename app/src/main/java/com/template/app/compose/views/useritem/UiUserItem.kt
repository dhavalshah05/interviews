package com.template.app.compose.views.useritem

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.template.app.R
import com.template.app.compose.Regular
import com.template.app.compose.fontPoppins

@Preview
@Composable
private fun PreviewUiUserItem() {
    UiUserItem()
}

@Composable
fun UiUserItem() {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "#1",
                fontSize = 18.sp,
                color = colorResource(id = R.color.textPrimary),
                fontFamily = fontPoppins,
                fontWeight = FontWeight.Regular,
                modifier = Modifier.padding(start = 20.dp),
            )
            Text(
                text = "User name",
                fontSize = 18.sp,
                color = colorResource(id = R.color.textPrimary),
                fontFamily = fontPoppins,
                fontWeight = FontWeight.Regular,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .weight(1f),
            )
            IconButton(
                onClick = {},
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = "Delete icon",
                    )
                }
            )
        }
        Divider(color = colorResource(id = R.color.divider))
    }
}