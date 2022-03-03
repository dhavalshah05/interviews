package com.template.app.util.permission

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

abstract class BasePermissionHandler(
    private val context: Context
) {

    protected var permissionListener: PermissionListener? = null

    /**
     *
     */
    private fun hasPermission(permissionString: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permissionString) == PackageManager.PERMISSION_GRANTED
    }

    /**
     *
     */
    protected fun canPerformAction(requiredPermissions: List<String>): Boolean {
        var canPerformAction = true
        for (permission in requiredPermissions) {
            if (!hasPermission(permission))
                canPerformAction = false
        }
        return canPerformAction
    }

    /**
     *
     */
    protected fun netPermissions(requiredPermissions: List<String>): Array<String> {
        val netPermissions = mutableListOf<String>()
        for (permission in requiredPermissions) {
            if (!hasPermission(permission))
                netPermissions.add(permission)
        }
        return netPermissions.toTypedArray()
    }

    /**
     *
     */
    abstract fun requirePermissions(listener: PermissionListener)

    /**
     *
     */
    abstract fun onRequestPermissionsResult(requestCode: Int)
}