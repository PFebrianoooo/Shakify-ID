package com.putrapebrianonurba.shakifyid.domain.usecase.setttings

import com.putrapebrianonurba.shakifyid.domain.model.Settings
import com.putrapebrianonurba.shakifyid.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsFlowUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    operator fun invoke(): Flow<Settings> {
        return repository.settingsFlow
    }
}