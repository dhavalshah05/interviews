package com.feature.managers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.data.managers.Manager
import com.data.managers.PreviewData
import com.service.presentation.R
import com.service.presentation.compose.views.button.ButtonPrimary
import com.service.presentation.compose.views.toolbar.ToolbarSecondary
import com.service.presentation.compose.views.useritem.UiUserItem
import com.service.presentation.utils.display.DisplayMetrics

@Preview
@Composable
private fun PreviewManagersScreen() {
    ManagersScreen(
        managers = PreviewData.getManagers(),
        onDeleteClick = {},
        onBackClick = {},
        onAddClick = {}
    )
}

@Composable
internal fun ManagersScreen(
    managers: List<Manager>,
    onBackClick: () -> Unit,
    onDeleteClick: (Manager) -> Unit,
    onAddClick: () -> Unit,
) {

    val lazyColumnPaddingBottom = remember {
        mutableStateOf(0.dp)
    }

    Box(
        modifier = Modifier.background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ToolbarSecondary(
                title = stringResource(id = R.string.screen_title_hr_managers),
                onBackClick = onBackClick,
            )
            LazyColumn(
                content = {
                    items(
                        items = managers,
                        key = { manager -> manager.id }
                    ) { manager ->
                        UiUserItem(
                            id = "#${manager.id}",
                            name = manager.name,
                            onDeleteClick = { onDeleteClick.invoke(manager) }
                        )
                    }
                },
                contentPadding = PaddingValues(bottom = lazyColumnPaddingBottom.value)
            )
        }
        ButtonPrimary(
            text = stringResource(id = R.string.button_add_new),
            onClick = onAddClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
                .onGloballyPositioned { layoutCoordinates ->
                    val buttonHeight = layoutCoordinates.size.height.toFloat()
                    val paddingVertical = DisplayMetrics.convertDpToPixel(40f)
                    val paddingInDp = DisplayMetrics
                        .convertPixelsToDp(buttonHeight + paddingVertical)
                        .toInt()
                    lazyColumnPaddingBottom.value = paddingInDp.dp
                },
        )
    }
}
