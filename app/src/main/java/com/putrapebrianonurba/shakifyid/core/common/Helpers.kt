package com.putrapebrianonurba.shakifyid.core.common

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import org.maplibre.android.geometry.LatLng
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

// CONVERTING STRING LITERAL LOCATION COORDINATES RESPONSE TO LAT LONG
fun String.toLatLng(): LatLng {
    val parts = this.split(",")
    val lat = parts[0].toDouble()
    val lon = parts[1].toDouble()
    return LatLng(lat, lon)
}

// CONVERTING STRING LITERAL LOCATION INTO FORMATTED LOCATION
// EXAMPLE: 25KM PULAU ARU-SULSEL -> Pulau Aru Sulsel
fun locationFormat(raw: String): String {
    return raw
        .substringAfterLast(" ")
        .replace("-", " ")
        .lowercase()
        .split(" ")
        .joinToString(" ") { it.replaceFirstChar { c -> c.uppercase() } }
}

// GET CURRENT DATE CONVERT TIME BASED ON FORMAT
fun String?.toFullDate(): String {
    val locale = Locale.forLanguageTag("id-ID")

    return OffsetDateTime.parse(this)
        .atZoneSameInstant(ZoneId.of("Asia/Jakarta"))
        .format(
            DateTimeFormatter.ofPattern(
                "dd MMM yyyy, HH:mm:ss z",
                locale
            )
        )
}

// REDIRECT OPEN URL BASED HTTP OR HTTPS URLS
fun redirectOpenUrl(
    context: Context,
    url: String
) {
    if (url.isBlank()) return

    val intent = Intent(Intent.ACTION_VIEW, url.toUri())
    context.startActivity(intent)
}

// GETTING LOCATION PERMISSION VALUE
fun Context.hasLocationPermission(): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
}

// REDIRECT TO SETTING FOR PERMISSIONS
fun Context.openAppSystemSettings() {
    startActivity(
        Intent().apply {
            setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            setData(Uri.fromParts("package", packageName, null))
        }
    )
}