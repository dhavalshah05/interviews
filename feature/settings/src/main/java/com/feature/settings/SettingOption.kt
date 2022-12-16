package com.feature.settings

import androidx.annotation.StringRes

internal data class SettingOption(
    val id: Long,
    @StringRes val title: Int
)
