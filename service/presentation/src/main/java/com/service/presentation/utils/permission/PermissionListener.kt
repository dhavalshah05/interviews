package com.service.presentation.utils.permission

interface PermissionListener {
    fun onPermissionsGranted()
    fun onPermissionRequestDenied()
    fun onPermissionRequestDeniedForever()
}