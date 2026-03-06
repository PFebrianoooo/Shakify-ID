package com.putrapebrianonurba.shakifyid.data.repository

import com.putrapebrianonurba.shakifyid.core.utils.NetworkError
import com.putrapebrianonurba.shakifyid.core.common.Resource
import com.putrapebrianonurba.shakifyid.data.mappers.toEarthquake
import com.putrapebrianonurba.shakifyid.data.remote.source.EarthquakeRemoteSource
import com.putrapebrianonurba.shakifyid.domain.model.Earthquake
import com.putrapebrianonurba.shakifyid.domain.repository.EarthquakeInformationRepository
import com.putrapebrianonurba.shakifyid.domain.repository.NetworkMonitorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class EarthquakeInformationRepositoryImpl @Inject constructor(
    private val earthquakeRemoteSource: EarthquakeRemoteSource,
    private val networkMonitor: NetworkMonitorRepository
): EarthquakeInformationRepository {
    override suspend fun fetchLatestEarthquakes(): Flow<Resource<List<Earthquake>>> = flow {
        // LOADING
        emit(Resource.Loading(true))

        // CHECK INTERNET CONNECTION
        if (!networkMonitor.isConnected.first()) {
            emit(Resource.NoInternet(true))
            emit(Resource.Loading(false))
            emit(Resource.Error(NetworkError.NO_INTERNET.message))
            return@flow
        }

        // DISABLE NETWORK CONNECTION TIMEOUT
        emit(Resource.NoInternet(false))

        // FETCH EARTHQUAKES FROM API
        try {
            val remoteData = earthquakeRemoteSource.fetchLatestEarthquakes()
            emit(Resource.Success(
                data = remoteData.earthquakeInformation.earthquake.map { it.toEarthquake() }
            ))
        } catch (_: IOException) {
            emit(Resource.Error(NetworkError.UNSTABLE_CONNECTION.message))
        } catch (_: HttpException) {
            emit(Resource.Error(NetworkError.HTTP_ERROR.message))
        } catch (_: Exception) {
            emit(Resource.Error(NetworkError.UNKNOWN.message))
        } finally {
            emit(Resource.Loading(false))
        }
    }
}