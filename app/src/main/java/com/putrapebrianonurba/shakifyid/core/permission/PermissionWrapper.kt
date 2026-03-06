package com.putrapebrianonurba.shakifyid.core.permission

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.putrapebrianonurba.shakifyid.core.components.EarthquakeDialog

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionWrapper(
    permission: String,
    onGranted: () -> Unit,
    content: @Composable () -> Unit
) {

    val context = LocalContext.current
    val permissionState = rememberPermissionState(permission)

    var showRationaleDialog by remember { mutableStateOf(false) }
    var showSettingsDialog by remember { mutableStateOf(false) }

    LaunchedEffect(permissionState.status) {
        when {
            permissionState.status.isGranted -> {
                showRationaleDialog = false
                showSettingsDialog = false
                onGranted()
            }

            permissionState.status.shouldShowRationale -> {
                showRationaleDialog = true
                showSettingsDialog = false
            }

            else -> {
                showRationaleDialog = true
            }
        }
    }

    if (permissionState.status.isGranted) {
        content()
    }

    EarthquakeDialog(
        isVisible = showRationaleDialog || showSettingsDialog,
        title = "Izin lokasi diperlukan!",
        description = "Aplikasi membutuhkan izin lokasi untuk menampilkan posisi Anda di peta.",
        onClick = {
            if (showRationaleDialog) {
                showRationaleDialog = false
                permissionState.launchPermissionRequest()
            } else {
                showSettingsDialog = false

                val intent = Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts("package", context.packageName, null)
                )

                context.startActivity(intent)
            }
        }
    )
}