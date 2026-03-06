package com.putrapebrianonurba.shakifyid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.putrapebrianonurba.shakifyid.core.theme.ShakifyIDTheme
import com.putrapebrianonurba.shakifyid.core.theme.SystemBars
import com.putrapebrianonurba.shakifyid.main_features.navigation.MainNavigation
import com.putrapebrianonurba.shakifyid.main_features.presentation.EarthquakeSettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            val viewModel: EarthquakeSettingsViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            ShakifyIDTheme(uiState.isDarkTheme) {
                SystemBars(uiState.isDarkTheme)
                MainNavigation()
            }
        }
    }
}