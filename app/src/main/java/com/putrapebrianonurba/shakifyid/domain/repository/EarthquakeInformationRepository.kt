package com.putrapebrianonurba.shakifyid.domain.repository

import com.putrapebrianonurba.shakifyid.core.common.Resource
import com.putrapebrianonurba.shakifyid.domain.model.Earthquake
import kotlinx.coroutines.flow.Flow

interface EarthquakeInformationRepository {
    suspend fun fetchLatestEarthquakes(): Flow<Resource<List<Earthquake>>>
}