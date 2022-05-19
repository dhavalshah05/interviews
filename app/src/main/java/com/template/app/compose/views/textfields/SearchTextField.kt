package com.template.app.compose.views.textfields

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.template.app.R
import com.template.app.compose.views.texts.AppTextStyle

@Preview
@Composable
private fun PreviewSearchTextField() {
    val searchText = remember { mutableStateOf("") }
    SearchTextField(
        value = searchText.value,
        onValueChange = { searchText.value = it },
        modifier = Modifier.padding(20.dp),
        onClearClick = { searchText.value = "" }
    )
}

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onClearClick: () -> Unit,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = colorResource(id = R.color.cardBorder), shape = RoundedCornerShape(4.dp)),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        placeholder = {
            Text(
                text = stringResource(id = R.string.hint_search),
                style = AppTextStyle.Regular,
                fontSize = 14.sp,
                color = colorResource(id = R.color.textSecondary)
            )
        },
        leadingIcon = {
            Icon(painter = painterResource(id = R.drawable.ic_search), contentDescription = "Search Icon", tint = Color.Unspecified)
        },
        trailingIcon = {
            if (value.isNotEmpty()) {
                IconButton(
                    onClick = onClearClick
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_cross), contentDescription = "Cancel Icon", tint = Color.Unspecified)
                }
            }
        },
        shape = RoundedCornerShape(4.dp),
        colors = TextFieldDefaults.textFieldColors(
            errorIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = colorResource(id = R.color.primary),
            errorCursorColor = colorResource(id = R.color.primary),
            textColor = colorResource(id = R.color.primary),
            backgroundColor = colorResource(id = R.color.cardBackground)
        ),
        textStyle = AppTextStyle.Regular.copy(
            fontSize = 14.sp
        ),
    )
}