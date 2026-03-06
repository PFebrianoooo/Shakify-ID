package com.putrapebrianonurba.shakifyid.data.remote.dto

import com.google.gson.annotations.SerializedName

data class EarthquakeDto(
    @SerializedName("DateTime")
    val dateTime: String,
    @SerializedName("Coordinates")
    val coordinates: String,
    @SerializedName("Lintang")
    val latitude: String,
    @SerializedName("Bujur")
    val longitude: String,
    @SerializedName("Magnitude")
    val magnitude: String,
    @SerializedName("Kedalaman")
    val depth: String,
    @SerializedName("Wilayah")
    val location: String,
    @SerializedName("Potensi")
    val potential: String
)
