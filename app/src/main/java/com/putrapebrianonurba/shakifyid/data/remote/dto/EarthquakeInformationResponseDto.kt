package com.putrapebrianonurba.shakifyid.data.remote.dto

import com.google.gson.annotations.SerializedName

data class EarthquakeInformationResponseDto(
    @SerializedName("Infogempa")
    val earthquakeInformation: EarthquakeInformationDto
)
