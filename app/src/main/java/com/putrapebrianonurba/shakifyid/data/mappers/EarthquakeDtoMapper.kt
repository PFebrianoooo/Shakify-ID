package com.putrapebrianonurba.shakifyid.data.mappers

import com.putrapebrianonurba.shakifyid.data.remote.dto.EarthquakeDto
import com.putrapebrianonurba.shakifyid.domain.model.Earthquake

// FROM DTO TO DOMAIN
fun EarthquakeDto.toEarthquake(): Earthquake {
    return Earthquake(
        dateTime = dateTime,
        coordinates = coordinates,
        latitude = latitude,
        longitude = longitude,
        magnitude = magnitude,
        depth = depth,
        location = location,
        potential = potential
    )
}