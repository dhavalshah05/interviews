package com.template.app.util.permission

import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class ActivityPermissionHandler(
    private val activity: AppCompatActivity,
    private val requiredPermissions: List<String>,
    private val requestCode: Int
) : BasePermissionHandler(activity) {

    private var imageRationalShown = false

    /**
     *
     */
    override fun requirePermissions(listener: PermissionListener) {
        permissionListener = listener

        when {
            canPerformAction(requiredPermissions) -> {
                permissionListener?.onPermissionsGranted()
            }
            shouldShowRational() -> {
                imageRationalShown = true
                ActivityCompat.requestPermissions(
                    activity,
                    netPermissions(requiredPermissions),
                    requestCode
                )
            }
            else -> {
                imageRationalShown = false
                ActivityCompat.requestPermissions(
                    activity,
                    netPermissions(requiredPermissions),
                    requestCode
                )
            }
        }
    }

    /**
     *
     */
    override fun onRequestPermissionsResult(requestCode: Int) {
        if (requestCode == this.requestCode) {
            if (canPerformAction(requiredPermissions)) {
                permissionListener?.onPermissionsGranted()
            } else if (!shouldShowRational()) {
                if (imageRationalShown) {
                    permissionListener?.onPermissionRequestDenied()
                } else {
                    permissionListener?.onPermissionRequestDeniedForever()
                }
            } else {
                permissionListener?.onPermissionRequestDenied()
            }
        }
    }


    /**
     *
     */
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