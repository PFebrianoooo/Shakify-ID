package com.putrapebrianonurba.shakifyid.domain.model

data class PermissionRequirement(
    val permissions: List<String>,
    val title: String,
    val description: String
)
