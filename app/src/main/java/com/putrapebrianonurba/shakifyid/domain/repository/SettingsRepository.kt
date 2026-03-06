package com.putrapebrianonurba.shakifyid.domain.repository

import com.putrapebrianonurba.shakifyid.domain.model.Settings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val settingsFlow: Flow<Settings>
    fun getInitialSettings(): Settings
    suspend fun setDarkMode(value: Boolean)
    suspend fun setNotificationEnabled(value: Boolean)
}