package com.putrapebrianonurba.shakifyid.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun EarthquakeCardShimmer() {
    val brush = shimmerEffect()

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            // MAGNITUDE CIRCLE SHIMMER
            ShimmerItem(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape),
                brush = brush
            )

            // EARTHQUAKE INFORMATION SHIMMER
            Column {

                // POTENTIAL
                ShimmerItem(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(16.dp)
                        .clip(RoundedCornerShape(6.dp)),
                    brush = brush
                )

                Spacer(modifier = Modifier.height(6.dp))

                // LOCATION
                ShimmerItem(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(14.dp)
                        .clip(RoundedCornerShape(6.dp)),
                    brush = brush
                )

                Spacer(modifier = Modifier.height(6.dp))

                // LAT LONG
                ShimmerItem(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(12.dp)
                        .clip(RoundedCornerShape(6.dp)),
                    brush = brush
                )
            }
        }
    }
}