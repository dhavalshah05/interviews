package com.service.presentation.model

data class Selectable<T>(
    val item: T,
    var selected: Boolean
)
