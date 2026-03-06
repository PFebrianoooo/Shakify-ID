package com.putrapebrianonurba.shakifyid.main_features.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.putrapebrianonurba.shakifyid.domain.usecase.setttings.InitialSettingsUseCase
import com.putrapebrianonurba.shakifyid.domain.usecase.setttings.SetDarkModeUseCase
import com.putrapebrianonurba.shakifyid.domain.usecase.setttings.SetNotificationEnabledUseCase
import com.putrapebrianonurba.shakifyid.domain.usecase.setttings.SettingsFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EarthquakeSettingsViewModel @Inject constructor(
    private val initialSettingsUseCase: InitialSettingsUseCase,
    private val settingsFlowUseCase: SettingsFlowUseCase,
    private val setDarkModeUseCase: SetDarkModeUseCase,
    private val setNotificationEnabledUseCase: SetNotificationEnabledUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(initialSettingsUseCase())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            settingsFlowUseCase().collectLatest {
                _uiState.value = it
            }
        }
    }

    fun onThemeToggle(theme: Boolean) {
        _uiState.update { it.copy(isDarkTheme = theme) }
        viewModelScope.launch { setDarkModeUseCase(theme) }
    }

    fun onNotificationToggle(notification: Boolean) {
        _uiState.update { it.copy(isNotificationEnabled = notification) }
        viewModelScope.launch { setNotificationEnabledUseCase(notification) }
    }
}