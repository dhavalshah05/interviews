package com.feature.managers.add

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.service.presentation.R
import com.service.presentation.compose.views.bottomsheet.BottomSheetContainer
import com.service.presentation.compose.views.bottomsheet.BottomSheetTitle
import com.service.presentation.compose.views.button.ButtonPrimary
import com.service.presentation.compose.views.textfield.AppTextField

@Preview
@Composable
private fun PreviewAddManagerScreen() {
    AddManagerScreen(
        onAddClick = {}
    )
}

@Composable
fun AddManagerScreen(
    onAddClick: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val managerName = remember {
        mutableStateOf("")
    }

    BottomSheetContainer(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(bottom = 80.dp, top = 20.dp),
        verticalSpaceBetweenContent = 20.dp
    ) {
        BottomSheetTitle(title = stringResource(id = R.string.label_add_manager))
        AppTextField(
            placeHolderText = "",
            value = managerName.value,
            onValueChange = { managerName.value = it },
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            )
        )
        ButtonPrimary(
            text = stringResource(id = R.string.button_add),
            onClick = { onAddClick.invoke(managerName.value) },
            paddingHorizontal = 30.dp
        )
    }
}
