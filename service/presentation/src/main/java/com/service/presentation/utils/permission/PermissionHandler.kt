package com.service.presentation.utils.permission

import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionHandler constructor(
    private val activity: ComponentActivity,
    private val requiredPermissions: List<String>,
    private val listener: PermissionListener,
) {

    private val activityResultLauncher: ActivityResultLauncher<Array<String>> =
        activity.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions(),
            getPermissionResultCallback()
        )

    private var imageRationalShown = false

    fun requirePermissions() {
        when {
            canPerformAction(requiredPermissions) -> {
                listener.onPermissionsGranted()
            }
            shouldShowRational() -> {
                imageRationalShown = true
                activityResultLauncher.launch(netPermissions(requiredPermissions))
            }
            else -> {
                imageRationalShown = false
                activityResultLauncher.launch(netPermissions(requiredPermissions))
            }
        }
    }

    private fun getPermissionResultCallback(): ActivityResultCallback<Map<String, Boolean>> {
        return ActivityResultCallback<Map<String, Boolean>> {
            if (canPerformAction(requiredPermissions)) {
                listener.onPermissionsGranted()
            } else if (!shouldShowRational()) {
                if (imageRationalShown) {
                    listener.onPermissionRequestDenied()
                } else {
                    listener.onPermissionRequestDeniedForever()
                }
            } else {
                listener.onPermissionRequestDenied()
            }
        }
    }

    private fun hasPermission(permissionString: String): Boolean {
        return ContextCompat.checkSelfPermission(
            activity,
            permissionString
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun canPerformAction(requiredPermissions: List<String>): Boolean {
        var canPerformAction = true
        for (permission in requiredPermissions) {
            if (!hasPermission(permission))
                canPerformAction = false
        }
        return canPerformAction
    }

    private fun netPermissions(requiredPermissions: List<String>): Array<String> {
        val netPermissions = mutableListOf<String>()
        for (permission in requiredPermissions) {
            if (!hasPermission(permission))
                netPermissions.add(permission)
        }
        return netPermissions.toTypedArray()
    }

    private fun shouldShowRational(): Boolean {
        var showRational = false
        for (permission in requiredPermissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                showRational = true
            }
        }
        return showRational
    }
}
