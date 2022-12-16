package com.service.presentation.utils.alert

import android.content.Context
import android.widget.Toast

internal class ToastAlertMessage(
    private val context: Context
) : AlertMessage {

    override fun success(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun error(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun info(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}