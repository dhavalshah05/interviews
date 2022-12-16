package com.service.presentation.compose.views.textfield

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.service.presentation.R
import com.service.presentation.compose.AppTextStyle

@Preview
@Composable
private fun PreviewAppTextField() {
    val searchText = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    AppTextField(
        value = searchText.value,
        onValueChange = { searchText.value = it },
        modifier = Modifier.padding(20.dp),
        placeHolderText = "Placeholder here...",
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
    )
}

@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    placeHolderText: String,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = colorResource(id = R.color.cardBorder), shape = RoundedCornerShape(4.dp)),
        singleLine = true,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        placeholder = {
            Text(
                text = placeHolderText,
                style = AppTextStyle.RegularSecondary,
            )
        },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
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
        textStyle = AppTextStyle.RegularPrimary.copy(
            fontSize = 14.sp
        ),
    )
}
