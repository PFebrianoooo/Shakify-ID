package com.putrapebrianonurba.shakifyid.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore("settings_preferences")

class SettingsDatastore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    val darkMode: Flow<Boolean> =
        context.dataStore.data.map {
            it[DARK_MODE] ?: false
        }

    val notificationEnabled: Flow<Boolean> =
        context.dataStore.data.map {
            it[NOTIFICATION_ENABLED] ?: true
        }

    suspend fun setDarkMode(value: Boolean) {
        context.dataStore.edit {
            it[DARK_MODE] = value
        }
    }

    suspend fun setNotificationEnabled(value: Boolean) {
        context.dataStore.edit {
            it[NOTIFICATION_ENABLED] = value
        }
    }

    companion object {
        val DARK_MODE = booleanPreferencesKey("dark_mode")
        val NOTIFICATION_ENABLED = booleanPreferencesKey("notification_enabled")
    }
}