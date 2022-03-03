package com.template.app.util.alert

import android.app.Activity
import android.app.Dialog
import com.tapadoo.alerter.Alerter
import com.template.app.R

class AlertNotification {

    fun showErrorMessage(
        message: String,
        activity: Activity? = null,
        dialog: Dialog? = null
    ) {
        val alerter = when {
            dialog != null -> {
                Alerter.create(dialog = dialog)
            }
            activity != null -> {
                Alerter.create(activity = activity)
            }
            else -> {
                throw RuntimeException("Both activity and dialog cannot be null for Alert Notification.")
            }
        }

        alerter.setText(message)
            //.setTextTypeface(ResourcesCompat.getFont(activity, R.font.FONT_NAME)!!)
            .setBackgroundColorRes(R.color.colorRedAlert)
            .hideIcon()
            .show()
    }

    fun showSuccessMessage(
        message: String,
        activity: Activity? = null,
        dialog: Dialog? = null
    ) {
        val alerter = when {
            dialog != null -> {
                Alerter.create(dialog = dialog)
            }
            activity != null -> {
                Alerter.create(activity = activity)
            }
            else -> {
                throw RuntimeException("Both activity and dialog cannot be null for Alert Notification.")
            }
        }

        alerter.setText(message)
            //.setTextTypeface(ResourcesCompat.getFont(activity, R.font.FONT_NAME)!!)
            .setBackgroundColorRes(R.color.colorGreenAlert)
            .hideIcon()
            .show()
    }

}