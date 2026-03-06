package com.putrapebrianonurba.shakifyid.domain.usecase.earthquake

import com.putrapebrianonurba.shakifyid.core.common.Resource
import com.putrapebrianonurba.shakifyid.domain.model.Earthquake
import com.putrapebrianonurba.shakifyid.domain.repository.EarthquakeInformationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchLatestEarthquakesUseCase @Inject constructor(
    private val repository: EarthquakeInformationRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<Earthquake>>> {
        return repository.fetchLatestEarthquakes()
    }
}