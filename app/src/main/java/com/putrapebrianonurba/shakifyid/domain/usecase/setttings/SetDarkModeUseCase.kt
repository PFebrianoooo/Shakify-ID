package com.putrapebrianonurba.shakifyid.domain.usecase.setttings

import com.putrapebrianonurba.shakifyid.domain.repository.SettingsRepository
import javax.inject.Inject

class SetDarkModeUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(value: Boolean) {
        return repository.setDarkMode(value)
    }
}