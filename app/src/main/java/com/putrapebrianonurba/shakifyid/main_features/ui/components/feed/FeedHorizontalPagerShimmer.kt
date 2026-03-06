package com.putrapebrianonurba.shakifyid.main_features.ui.components.feed

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.putrapebrianonurba.shakifyid.core.components.EarthquakeCardShimmer

@Composable
fun FeedHorizontalPagerShimmer(
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState { 5 }

    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxWidth()
    ) { page ->
        EarthquakeCardShimmer()
    }
}