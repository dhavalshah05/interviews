package com.template.app.compose.views.buttons

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.template.app.R
import com.template.app.compose.fontPoppins

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
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        content = {
            Text(
                text = text,
                color = colorResource(id = R.color.white),
                fontSize = 14.sp,
                fontFamily = fontPoppins,
                fontWeight = FontWeight.SemiBold
            )
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.primary)
        ),
        shape = RoundedCornerShape(100.dp),
        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 12.dp)
    )
}