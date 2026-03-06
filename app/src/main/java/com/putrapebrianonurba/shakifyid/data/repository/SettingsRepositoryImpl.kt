package com.putrapebrianonurba.shakifyid.data.repository

import com.putrapebrianonurba.shakifyid.data.local.source.SettingsLocalSource
import com.putrapebrianonurba.shakifyid.domain.model.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepositoryImpl @Inject constructor(
    private val settingsLocalSource: SettingsLocalSource
): com.putrapebrianonurba.shakifyid.domain.repository.SettingsRepository {
    override val settingsFlow: Flow<Settings> = settingsLocalSource.settingsFlow

    override fun getInitialSettings(): Settings {
        return runBlocking { settingsFlow.first() }
    }

    override suspend fun setDarkMode(value: Boolean) {
        settingsLocalSource.setDarkMode(value)
    }

    override suspend fun setNotificationEnabled(value: Boolean) {
        settingsLocalSource.setNotificationEnabled(value)
    }

}