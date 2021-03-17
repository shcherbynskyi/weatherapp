package com.example.weather_app.data.datasources.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather_app.data.models.weather.WeatherApiResponse

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weather: WeatherApiResponse)

    @Query("select * from weather")
    fun fetchAll(): List<WeatherApiResponse>

    @Query("select * from weather where name = :cityName")
    fun fetchByCityName(cityName: String): WeatherApiResponse?

    @Query("select * from weather where id = :cityId")
    fun fetchByCityId(cityId: Int): WeatherApiResponse?
}