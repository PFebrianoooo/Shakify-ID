package com.putrapebrianonurba.shakifyid.domain.usecase.setttings

import com.putrapebrianonurba.shakifyid.domain.model.Settings
import com.putrapebrianonurba.shakifyid.domain.repository.SettingsRepository
import javax.inject.Inject

class InitialSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    operator fun invoke(): Settings {
        return repository.getInitialSettings()
    }
}