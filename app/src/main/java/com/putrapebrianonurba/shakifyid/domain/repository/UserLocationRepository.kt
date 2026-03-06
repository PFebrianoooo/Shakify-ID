package com.putrapebrianonurba.shakifyid.domain.repository

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface UserLocationRepository {
    fun streamCurrentLocation(): Flow<Location>
}