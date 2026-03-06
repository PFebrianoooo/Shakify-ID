package com.putrapebrianonurba.shakifyid.main_features.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.putrapebrianonurba.shakifyid.core.common.Resource
import com.putrapebrianonurba.shakifyid.domain.usecase.earthquake.FetchLatestEarthquakesUseCase
import com.putrapebrianonurba.shakifyid.domain.usecase.location.StreamUserLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.maplibre.android.geometry.LatLng
import javax.inject.Inject

@HiltViewModel
class EarthquakeFeedViewModel @Inject constructor(
    private val fetchLatestEarthquakes: FetchLatestEarthquakesUseCase,
    private val streamUserLocation: StreamUserLocationUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(EarthquakeFeedUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadEarthquakes()
    }

    // EARTHQUAKES
    fun loadEarthquakes() {
        viewModelScope.launch {
            fetchLatestEarthquakes().collect { result ->
                _uiState.update { current ->
                    when(result) {
                        is Resource.NoInternet -> {
                            current.copy(isNoInternet = result.noInternet)
                        }
                        is Resource.Error -> {
                            current.copy(errorMessage = result.message)
                        }
                        is Resource.Loading -> {
                            current.copy(isLoading = result.isLoading)
                        }
                        is Resource.Success -> {
                            current.copy(
                                earthquakes = result.data ?: emptyList(),
                                selectedEarthquake = result.data?.firstOrNull()
                            )
                        }
                    }
                }
            }
        }
    }

    fun setSelectedEarthquake(selectedEarthquake: Int) {
        val list = _uiState.value.earthquakes
        if (selectedEarthquake !in list.indices) return

        _uiState.update {
            it.copy(selectedEarthquake = list[selectedEarthquake])
        }
    }

    fun toggleSheet() {
        _uiState.update { it.copy(showSheet = !_uiState.value.showSheet) }
    }

    // USER LOCATION STREAM
    fun startLocationStream() {
        viewModelScope.launch {
            streamUserLocation.invoke().collect {
                _uiState.update { current ->
                    current.copy(
                        location = LatLng(it.latitude, it.longitude)
                    )
                }
            }
        }
    }
}