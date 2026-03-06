package com.putrapebrianonurba.shakifyid.data.remote.dto

import com.google.gson.annotations.SerializedName

data class EarthquakeInformationDto(
    @SerializedName("gempa")
    val earthquake: List<EarthquakeDto>
)
