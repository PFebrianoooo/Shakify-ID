package com.putrapebrianonurba.shakifyid.data.local.source

import com.putrapebrianonurba.shakifyid.data.local.datastore.SettingsDatastore
import com.putrapebrianonurba.shakifyid.domain.model.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class SettingsLocalSource @Inject constructor(
    private val settingsDatastore: SettingsDatastore
) {
    val settingsFlow: Flow<Settings> =
        combine(
            settingsDatastore.darkMode,
            settingsDatastore.notificationEnabled
        ) { darkMode, notification ->
            Settings(
                isDarkTheme = darkMode,
                isNotificationEnabled = notification
            )
        }

    suspend fun setDarkMode(value: Boolean) {
        settingsDatastore.setDarkMode(value)
    }

    suspend fun setNotificationEnabled(value: Boolean) {
        settingsDatastore.setNotificationEnabled(value)
    }
}