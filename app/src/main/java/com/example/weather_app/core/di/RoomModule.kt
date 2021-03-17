package com.example.weather_app.core.di

import android.app.Application
import androidx.room.Room
import com.example.weather_app.core.room.AppDatabase
import com.example.weather_app.data.datasources.cache.ForecastDao
import com.example.weather_app.data.datasources.cache.WeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DATABASE_NAME = "weather-app-database"

    @Provides
    @Singleton
    fun providesAppDatabase(app: Application): AppDatabase = Room
        .databaseBuilder(app.applicationContext, AppDatabase::class.java, DATABASE_NAME)
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun providesWeatherDao(db: AppDatabase): WeatherDao = db.weatherDao()

    @Provides
    @Singleton
    fun providesForecastDao(db: AppDatabase): ForecastDao = db.forecastDao()
}