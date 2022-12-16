package com.service.presentation.utils.alert

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import com.service.presentation.utils.display.DisplayMetrics
import es.dmoral.toasty.Toasty

internal class ToastyAlertMessage(
    private val context: Context
) : AlertMessage {

    init {
        val top = DisplayMetrics.convertDpToPixel(56F).toInt()
        Toasty.Config.getInstance()
            .allowQueue(false)
            .setGravity(Gravity.TOP, 0, top)
            .apply()
    }

    override fun success(message: String) {
        Toasty.success(context, message, Toast.LENGTH_SHORT, true).show()
    }

    override fun error(message: String) {
        Toasty.error(context, message, Toast.LENGTH_SHORT, true).show()
    }

    override fun info(message: String) {
        Toasty.info(context, message, Toast.LENGTH_SHORT, true).show()
    }
}