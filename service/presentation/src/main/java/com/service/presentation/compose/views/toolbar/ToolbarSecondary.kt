package com.service.presentation.compose.views.toolbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.service.presentation.R
import com.service.presentation.compose.AppTextStyle

@Preview
@Composable
private fun PreviewToolbarSecondary() {
    ToolbarSecondary(
        title = "Toolbar Title",
        onBackClick = {},
    )
}

@Composable
fun ToolbarSecondary(
    modifier: Modifier = Modifier,
    title: String,
    onBackClick: () -> Unit,
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    actions: (@Composable RowScope.() -> Unit)? = null
) {
    TopAppBar(
        backgroundColor = Color.White,
        modifier = modifier,
        elevation = elevation
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "",
                tint = colorResource(id = R.color.textPrimary)
            )
        }
        Text(
            text = title,
            style = AppTextStyle.MediumPrimary.copy(
                fontSize = 20.sp,
            ),
            modifier = Modifier.weight(1F),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        if (actions != null) {
            Row(content = actions)
        }
    }
}
