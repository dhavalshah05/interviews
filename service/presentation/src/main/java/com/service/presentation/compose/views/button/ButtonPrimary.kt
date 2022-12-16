package com.service.presentation.compose.views.button

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.service.presentation.R
import com.service.presentation.compose.AppTextStyle

@Preview(showBackground = true)
@Composable
private fun PreviewButtonPrimary() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        ButtonPrimary(
            text = "Primary button",
            onClick = {}
        )
    }
}

@Composable
fun ButtonPrimary(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    paddingHorizontal: Dp = 18.dp
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        content = {
            Text(
                text = text,
                style = AppTextStyle.SemiBoldPrimary.copy(color = colorResource(id = R.color.white))
            )
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.primary)
        ),
        shape = RoundedCornerShape(100.dp),
        contentPadding = PaddingValues(horizontal = paddingHorizontal, vertical = 12.dp)
    )
}