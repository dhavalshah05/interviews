package com.template.app.ui.interviewers.addinterviewer

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
import com.template.app.R
import com.template.app.compose.views.bottomsheet.BottomSheetContainer
import com.template.app.compose.views.bottomsheet.BottomSheetTitle
import com.template.app.compose.views.buttons.ButtonPrimary
import com.template.app.compose.views.textfields.AppTextField

@Preview
@Composable
private fun PreviewAddInterviewerScreen() {
    AddInterviewerScreen(
        onAddClick = {}
    )
}

@Composable
fun AddInterviewerScreen(
    onAddClick: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val interviewerName = remember {
        mutableStateOf("")
    }

    BottomSheetContainer(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(bottom = 80.dp, top = 20.dp),
        verticalSpaceBetweenContent = 20.dp
    ) {
        BottomSheetTitle(title = stringResource(id = R.string.label_add_interviewer))
        AppTextField(
            placeHolderText = "",
            value = interviewerName.value,
            onValueChange = { interviewerName.value = it },
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            )
        )
        ButtonPrimary(
            text = stringResource(id = R.string.button_add),
            onClick = { onAddClick.invoke(interviewerName.value) },
            paddingHorizontal = 30.dp
        )
    }
}