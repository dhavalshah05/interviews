package com.template.app.ui.common.models

data class Selectable<T>(
    val item: T,
    var selected: Boolean
)