package com.feature.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.service.presentation.R
import com.service.presentation.compose.views.toolbar.ToolbarSecondary

@Preview
@Composable
private fun PreviewSettingsScreen() {
    SettingsScreen(
        settingOptions = listOf(
            SettingOption(id = 1, title = R.string.label_hr_managers),
            SettingOption(id = 2, title = R.string.label_interviewers),
        ),
        onBackClick = {},
        onSettingOptionClick = {}
    )
}

@Composable
internal fun SettingsScreen(
    settingOptions: List<SettingOption>,
    onBackClick: () -> Unit,
    onSettingOptionClick: (SettingOption) -> Unit
) {
    Box(
        modifier = Modifier.background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ToolbarSecondary(
                title = stringResource(id = R.string.screen_title_settings),
                onBackClick = onBackClick,
            )
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                content = {
                    items(
                        items = settingOptions,
                        key = { settingOption -> settingOption.id }
                    ) { settingOption ->
                        SettingItem(
                            title = stringResource(id = settingOption.title),
                            onClick = { onSettingOptionClick.invoke(settingOption) },
                        )
                    }
                },
            )
        }
    }
}