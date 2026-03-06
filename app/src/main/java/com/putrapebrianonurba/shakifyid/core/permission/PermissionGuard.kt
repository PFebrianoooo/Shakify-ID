package com.putrapebrianonurba.shakifyid.core.permission

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.putrapebrianonurba.shakifyid.core.common.openAppSystemSettings
import com.putrapebrianonurba.shakifyid.core.components.EarthquakeDialog
import com.putrapebrianonurba.shakifyid.domain.model.PermissionRequirement

@Composable
fun PermissionGuard(
    requirement: PermissionRequirement,
    onPermissionGranted: () -> Unit,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val activity = context as Activity

    val manager = remember { PermissionManager(context) }

    var state by remember { mutableStateOf<PermissionState>(PermissionState.Granted) }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        if (result.values.all { it }) {
            state = PermissionState.Granted
            onPermissionGranted()
        } else {
            state = manager.getStatus(activity, requirement)
        }
    }

    LaunchedEffect(Unit) {
        state = manager.getStatus(activity, requirement)
        if (state is PermissionState.Granted) onPermissionGranted()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        content()


        if (state !is PermissionState.Granted) {
            val isPermanent = state is PermissionState.PermanentlyDenied

            EarthquakeDialog(
                isVisible = true,
                title = requirement.title,
                description = requirement.description,
                onClick = {
                    if (isPermanent) {
                        context.openAppSystemSettings()
                    } else {
                        launcher.launch(requirement.permissions.toTypedArray())
                    }
                }
            )
        }
    }
}