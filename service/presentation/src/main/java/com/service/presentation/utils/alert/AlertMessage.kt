package com.service.presentation.utils.alert

interface AlertMessage {
    fun success(message: String)
    fun error(message: String)
    fun info(message: String)
}
