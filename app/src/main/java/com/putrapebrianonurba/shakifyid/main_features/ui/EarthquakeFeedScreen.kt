package com.putrapebrianonurba.shakifyid.main_features.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.putrapebrianonurba.shakifyid.core.common.toLatLng
import com.putrapebrianonurba.shakifyid.core.components.EarthquakeBottomSheet
import com.putrapebrianonurba.shakifyid.core.components.EarthquakeCardRefresh
import com.putrapebrianonurba.shakifyid.core.components.Map
import com.putrapebrianonurba.shakifyid.core.permission.PermissionGuard
import com.putrapebrianonurba.shakifyid.core.permission.PermissionRegistry
import com.putrapebrianonurba.shakifyid.main_features.presentation.EarthquakeFeedViewModel
import com.putrapebrianonurba.shakifyid.main_features.ui.components.feed.FeedHorizontalPager
import com.putrapebrianonurba.shakifyid.main_features.ui.components.feed.FeedHorizontalPagerShimmer
import com.putrapebrianonurba.shakifyid.main_features.ui.components.feed.FeedTopBar
import org.maplibre.android.geometry.LatLng

@Composable
fun EarthquakeFeedScreen(
    viewModel: EarthquakeFeedViewModel,
    onNavigateSettings: () -> Unit
) {
    // UI STATE
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // PAGER STATE
    val pagerState = rememberPagerState(pageCount = { uiState.earthquakes.size })

    // EFFECTS HANDLER FOR CHANGING TARGET MAP, BASED ON PAGER SELECTED
    LaunchedEffect(pagerState, uiState.earthquakes) {
        snapshotFlow { pagerState.currentPage }
            .collect { viewModel.setSelectedEarthquake(it) }
    }

    // PERMISSION GUARD
    PermissionGuard(
        requirement = PermissionRegistry.Location,
        onPermissionGranted = { viewModel.startLocationStream() }
    ) {
        // CONTAINER
        Scaffold(
            // TOP BAR
            topBar = { FeedTopBar(onNavigateSettings) },
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                // MAP
                Map(
                    earthquakeCamera = uiState.selectedEarthquake?.coordinates?.toLatLng() ?: LatLng(-6.2, 106.8),
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