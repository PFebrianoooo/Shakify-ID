package com.putrapebrianonurba.shakifyid.core.permission

import com.putrapebrianonurba.shakifyid.domain.model.PermissionRequirement

object PermissionRegistry {
    val Location = PermissionRequirement(
        permissions = listOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ),
        title = "Izin akses untuk lokasi terkini kamu, ya.",
        description = "Agar bisa ambil informasi lokasi terkini dari gempa bumi, yang berada di sekitar kamu. Kamu perlu aktifkan izin lokasi di HP-mu."
    )
}