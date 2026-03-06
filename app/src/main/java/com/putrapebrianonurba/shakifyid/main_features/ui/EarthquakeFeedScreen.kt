package com.putrapebrianonurba.shakifyid.main_features.ui

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.putrapebrianonurba.shakifyid.core.common.toLatLng
import com.putrapebrianonurba.shakifyid.core.components.EarthquakeBottomSheet
import com.putrapebrianonurba.shakifyid.core.components.EarthquakeCardRefresh
import com.putrapebrianonurba.shakifyid.core.components.Map
import com.putrapebrianonurba.shakifyid.core.permission.PermissionWrapper
import com.putrapebrianonurba.shakifyid.main_features.presentation.EarthquakeFeedViewModel
import com.putrapebrianonurba.shakifyid.main_features.ui.components.feed.FeedHorizontalPager
import com.putrapebrianonurba.shakifyid.main_features.ui.components.feed.FeedHorizontalPagerShimmer
import com.putrapebrianonurba.shakifyid.main_features.ui.components.feed.FeedTopBar

@Composable
fun EarthquakeFeedScreen(
    viewModel: EarthquakeFeedViewModel,
    onNavigateSettings: () -> Unit
) {
    // UI STATE
    val uiState by viewModel.uiState.collectAsState()

    // PAGER STATE
    val pagerState = rememberPagerState(pageCount = { uiState.earthquakes.size })

    // EFFECTS HANDLER FOR CHANGING TARGET MAP, BASED ON PAGER SELECTED
    LaunchedEffect(pagerState, uiState.earthquakes) {
        snapshotFlow { pagerState.currentPage }
            .collect { viewModel.setSelectedEarthquake(it) }
    }
    // CONTAINER
    Scaffold(
        // TOP BAR
        topBar = { FeedTopBar(onNavigateSettings) },
    ) { innerPadding ->
        // PERMISSION WRAPPER
        PermissionWrapper(
            permission = Manifest.permission.ACCESS_FINE_LOCATION,
            onGranted = { viewModel.startLocationStream() }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                // MAP
                Map(
                    earthquakeCamera = uiState.selectedEarthquake?.coordinates?.toLatLng()
                        ?: uiState.location,
                    earthquakesMarker = uiState.earthquakes.map { it.coordinates.toLatLng() },
                )

                // HORIZONTAL PAGER EARTHQUAKES
                when {
                    uiState.isNoInternet -> {
                        EarthquakeCardRefresh(
                            onRetry = { viewModel.loadEarthquakes() },
                            modifier = Modifier.align(Alignment.BottomCenter)
                        )
                    }
                    uiState.isLoading -> {
                        FeedHorizontalPagerShimmer(
                            modifier = Modifier.align(Alignment.BottomCenter)
                        )
                    }
                    else -> {
                        FeedHorizontalPager(
                            pagerState = pagerState,
                            earthquakes = uiState.earthquakes,
                            onNavigateDetail = { viewModel.toggleSheet() },
                            modifier = Modifier.align(Alignment.BottomCenter)
                        )

                        // SHEET
                        EarthquakeBottomSheet(
                            earthquake = uiState.selectedEarthquake,
                            isVisible = uiState.showSheet,
                            onDismiss = { viewModel.toggleSheet() }
                        )
                    }
                }
            }
        }
    }
}