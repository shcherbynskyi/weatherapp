package com.example.weather_app.core.di

import com.example.weather_app.data.datasources.cache.WeatherDao
import com.example.weather_app.data.datasources.remote.OpenWeatherApi
import com.example.weather_app.data.repositories.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesWeatherRepository(
        api: OpenWeatherApi,
        dao: WeatherDao
    ): WeatherRepository = WeatherRepository(api, dao)

}