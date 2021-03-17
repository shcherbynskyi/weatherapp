package com.example.weather_app.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weather_app.core.room.converters.*
import com.example.weather_app.data.datasources.cache.ForecastDao
import com.example.weather_app.data.datasources.cache.WeatherDao
import com.example.weather_app.data.models.forecast.ForecastApiResponse
import com.example.weather_app.data.models.weather.WeatherApiResponse

@Database(
    entities = [
        WeatherApiResponse::class, ForecastApiResponse::class
    ], version = 2
)
@TypeConverters(
    CityConverter::class,
    CloudsConverter::class,
    CoordinatesConverter::class,
    DailyForecastConverter::class,
    MainConverter::class,
    SysConverter::class,
    WeatherTypeConverter::class,
    WindConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
    abstract fun forecastDao(): ForecastDao
}