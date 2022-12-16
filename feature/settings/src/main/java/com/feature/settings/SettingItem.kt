package com.feature.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.service.presentation.compose.AppTextStyle
import com.service.presentation.R

@Preview
@Composable
private fun PreviewSettingItem() {
    SettingItem(
        title = "Setting item",
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
        onClick = {}
    )
}

@Composable
internal fun SettingItem(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(width = 1.dp, color = colorResource(id = R.color.cardBorder)),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.cardBackground)),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 15.dp)
    ) {
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = AppTextStyle.MediumPrimary,
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = "Right Arrow",
            tint = colorResource(id = R.color.primary)
        )
    }
}