package com.putrapebrianonurba.shakifyid.main_features.presentation

import com.putrapebrianonurba.shakifyid.domain.model.Earthquake

data class EarthquakeFeedUiState(
    // EXCEPTION
    val isNoInternet: Boolean = false,
    val isLoading: Boolean = true,
    val errorMessage: String? = null,

    // DATA
    val earthquakes: List<Earthquake> = emptyList(),
    val selectedEarthquake: Earthquake? = null,
    val showSheet: Boolean = false,
)