package com.putrapebrianonurba.shakifyid.di

import com.putrapebrianonurba.shakifyid.data.repository.EarthquakeInformationRepositoryImpl
import com.putrapebrianonurba.shakifyid.data.repository.NetworkMonitorRepositoryImpl
import com.putrapebrianonurba.shakifyid.data.repository.SettingsRepositoryImpl
import com.putrapebrianonurba.shakifyid.data.repository.UserLocationRepositoryImpl
import com.putrapebrianonurba.shakifyid.domain.repository.EarthquakeInformationRepository
import com.putrapebrianonurba.shakifyid.domain.repository.NetworkMonitorRepository
import com.putrapebrianonurba.shakifyid.domain.repository.SettingsRepository
import com.putrapebrianonurba.shakifyid.domain.repository.UserLocationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryBinding {
    @Binds
    @Singleton
    abstract fun bindNetworkMonitorRepository(
        impl: NetworkMonitorRepositoryImpl
    ): NetworkMonitorRepository

    @Binds
    @Singleton
    abstract fun bindEarthquakeInformationRepository(
        impl: EarthquakeInformationRepositoryImpl
    ): EarthquakeInformationRepository

    @Binds
    @Singleton
    abstract fun bindSettingsRepository(
        impl: SettingsRepositoryImpl
    ): SettingsRepository

    @Binds
    @Singleton
    abstract fun bindUserLocationRepository(
        impl: UserLocationRepositoryImpl
    ): UserLocationRepository
}