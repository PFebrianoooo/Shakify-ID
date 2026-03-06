package com.putrapebrianonurba.shakifyid.domain.model

data class Earthquake(
    val dateTime: String,
    val coordinates: String,
    val latitude: String,
    val longitude: String,
    val magnitude: String,
    val depth: String,
    val location: String,
    val potential: String
)
