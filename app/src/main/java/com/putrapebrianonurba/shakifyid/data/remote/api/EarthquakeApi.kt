package com.putrapebrianonurba.shakifyid.data.remote.api

import com.putrapebrianonurba.shakifyid.data.remote.dto.EarthquakeInformationResponseDto
import retrofit2.http.GET

interface EarthquakeApi {
    // GET 15 LATEST EARTHQUAKE
    @GET("gempaterkini.json")
    suspend fun getLatestEarthquakes(): EarthquakeInformationResponseDto
}