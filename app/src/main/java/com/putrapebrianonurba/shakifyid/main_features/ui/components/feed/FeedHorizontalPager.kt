package com.putrapebrianonurba.shakifyid.main_features.ui.components.feed

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.putrapebrianonurba.shakifyid.core.components.EarthquakeCard
import com.putrapebrianonurba.shakifyid.domain.model.Earthquake

@Composable
fun FeedHorizontalPager(
    pagerState: PagerState,
    earthquakes: List<Earthquake>,
    onNavigateDetail: () -> Unit,
    modifier: Modifier = Modifier
) {
    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxWidth(),
    ) { indexes ->
        EarthquakeCard(
            earthquake = earthquakes[indexes],
            onClick = { onNavigateDetail() }
        )
    }
}