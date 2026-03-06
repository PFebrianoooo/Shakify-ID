package com.putrapebrianonurba.shakifyid.core.permission

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.putrapebrianonurba.shakifyid.domain.model.PermissionRequirement
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PermissionManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getStatus(activity: Activity, requirement: PermissionRequirement): PermissionState {
        val isGranted = requirement.permissions.all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }

        if (isGranted) return PermissionState.Granted

        val shouldShowRationale = requirement.permissions.any {
            ActivityCompat.shouldShowRequestPermissionRationale(activity, it)
        }

        return if (shouldShowRationale) {
            PermissionState.Required
        } else {
            PermissionState.PermanentlyDenied
        }
    }
}