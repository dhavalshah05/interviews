package com.template.app.util.permission

import androidx.fragment.app.Fragment

class FragmentPermissionHandler(
    private val fragment: Fragment,
    private val requiredPermissions: List<String>,
    private val requestCode: Int
) : BasePermissionHandler(fragment.requireContext()) {

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
                fragment.requestPermissions(
                    netPermissions(requiredPermissions),
                    requestCode
                )
            }
            else -> {
                imageRationalShown = false
                fragment.requestPermissions(
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
            if (fragment.shouldShowRequestPermissionRationale(permission)) {
                showRational = true
            }
        }
        return showRational
    }

}