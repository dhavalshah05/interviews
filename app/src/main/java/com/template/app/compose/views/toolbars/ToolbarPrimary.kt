package com.template.app.compose.views.toolbars

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.template.app.R
import com.template.app.compose.views.texts.AppTextStyle

@Preview
@Composable
private fun PreviewToolbarPrimary() {
    ToolbarPrimary(
        title = stringResource(id = R.string.screen_title_interviews),
        onSettingsClick = {}
    )
}

@Composable
fun ToolbarPrimary(
    modifier: Modifier = Modifier,
    title: String,
    onSettingsClick: () -> Unit
) {
    TopAppBar(
        backgroundColor = Color.White,
        modifier = modifier,
        elevation = 0.dp
    ) {
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = title,
            style = AppTextStyle.MediumPrimary.copy(fontSize = 20.sp),
            modifier = Modifier.weight(1F)
        )
        IconButton(onClick = onSettingsClick) {
            Icon(
                painter = painterResource(id = R.drawable.icon_settings),
                contentDescription = "",
                tint = colorResource(id = R.color.textPrimary)
            )
        }
    }
}