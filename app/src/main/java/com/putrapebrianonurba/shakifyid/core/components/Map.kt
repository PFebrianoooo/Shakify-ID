package com.putrapebrianonurba.shakifyid.core.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.putrapebrianonurba.shakifyid.R
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.Style
import org.ramani.compose.CameraPosition
import org.ramani.compose.MapLibre
import org.ramani.compose.Margins
import org.ramani.compose.Symbol
import org.ramani.compose.UiSettings

@SuppressLint("LocalContextResourcesRead")
@Composable
fun Map(
    earthquakeCamera: LatLng,
    earthquakesMarker: List<LatLng>,
) {
    val context = LocalContext.current
    val mapStyle = remember {
        context.resources
            .openRawResource(R.raw.map_style)
            .bufferedReader()
            .use { it.readText() }
    }

    val cameraPosition = remember(earthquakeCamera) {
        CameraPosition(
            target = earthquakeCamera,
            zoom = 6.0
        )
    }

    // MAP
    MapLibre(
        modifier = Modifier.fillMaxSize(),
        styleBuilder = Style.Builder().fromJson(mapStyle),
        cameraPosition = cameraPosition,
        uiSettings = UiSettings(
            isAttributionEnabled = false,
            rotateGesturesEnabled = false,
            isLogoEnabled = true,
            logoMargins = Margins(left = 20, bottom = 260)
        )
    ) {
        // MARKERS
        earthquakesMarker.forEach { earthquake ->
            Symbol(
                center = earthquake,
                size = 0.8f,
                color = "#D32F2FFF"
            )
        }
    }
}