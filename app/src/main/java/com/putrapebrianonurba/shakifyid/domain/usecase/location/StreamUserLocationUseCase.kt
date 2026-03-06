package com.putrapebrianonurba.shakifyid.domain.usecase.location

import android.location.Location
import com.putrapebrianonurba.shakifyid.domain.repository.UserLocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StreamUserLocationUseCase @Inject constructor(
    private val repository: UserLocationRepository
) {
    operator fun invoke(): Flow<Location> {
        return repository.streamCurrentLocation()
    }
}