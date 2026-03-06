package com.putrapebrianonurba.shakifyid.di

import com.putrapebrianonurba.shakifyid.BuildConfig
import com.putrapebrianonurba.shakifyid.data.remote.api.EarthquakeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {
    // NETWORKING PROVIDERS

    // INTERCEPTOR
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if(BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            })
            .build()
    }

    // RETROFIT
    @Provides
    @Singleton
    fun provideEarthquakeRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    // PROVIDE EARTHQUAKE API
    @Provides
    fun provideEarthquakeApi(
        earthquakeRetrofit: Retrofit
    ): EarthquakeApi {
        return earthquakeRetrofit.create(EarthquakeApi::class.java)
    }
}