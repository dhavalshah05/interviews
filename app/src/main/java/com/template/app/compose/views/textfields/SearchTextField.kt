package com.template.app.compose.views.textfields

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.template.app.R

@Preview
@Composable
private fun PreviewSearchTextField() {
    val searchText = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    SearchTextField(
        value = searchText.value,
        onValueChange = { searchText.value = it },
        modifier = Modifier.padding(20.dp),
        onClearClick = { searchText.value = "" },
        onDoneClick = { focusManager.clearFocus() }
    )
}

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onClearClick: () -> Unit,
    onDoneClick: () -> Unit
) {
    AppTextField(
        placeHolderText = stringResource(id = R.string.hint_search),
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
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
        keyboardActions = KeyboardActions(
            onDone = { onDoneClick.invoke() }
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        )
    )
}