package com.putrapebrianonurba.shakifyid.data.repository

import android.content.Context
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.putrapebrianonurba.shakifyid.domain.repository.UserLocationRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class UserLocationRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
): UserLocationRepository {

    override fun streamCurrentLocation(): Flow<Location> = callbackFlow {
        val client = LocationServices.getFusedLocationProviderClient(context)

        val request = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            3000L)
            .build()

        val callback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                result.lastLocation?.let { trySend(it) }
            }
        }

        try {
            client.requestLocationUpdates(request, callback, Looper.getMainLooper())
        } catch (e: SecurityException) {
            close(e)
        }

        awaitClose {
            client.removeLocationUpdates(callback)
        }
    }
}
