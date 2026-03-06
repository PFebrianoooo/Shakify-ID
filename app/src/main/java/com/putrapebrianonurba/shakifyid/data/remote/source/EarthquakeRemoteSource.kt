package com.putrapebrianonurba.shakifyid.data.remote.source

import com.putrapebrianonurba.shakifyid.data.remote.api.EarthquakeApi
import com.putrapebrianonurba.shakifyid.data.remote.dto.EarthquakeInformationResponseDto
import javax.inject.Inject

class EarthquakeRemoteSource @Inject constructor(
    private val earthquakeApi: EarthquakeApi
) {
    suspend fun fetchLatestEarthquakes(): EarthquakeInformationResponseDto {
        return earthquakeApi.getLatestEarthquakes()
    }
}