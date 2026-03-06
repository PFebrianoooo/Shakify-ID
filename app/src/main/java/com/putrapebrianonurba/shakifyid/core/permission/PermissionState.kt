package com.putrapebrianonurba.shakifyid.core.permission

sealed class PermissionState {
    object Granted : PermissionState()
    object Required : PermissionState()
    object PermanentlyDenied : PermissionState()
}