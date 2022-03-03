package com.template.app.util.permission

interface PermissionListener {
    fun onPermissionsGranted()
    fun onPermissionRequestDenied()
    fun onPermissionRequestDeniedForever()
}