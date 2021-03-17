package com.example.weather_app.data.datasources.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather_app.data.models.forecast.ForecastApiResponse

@Dao
interface ForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(forecast: ForecastApiResponse)

    @Query("select * from forecast where cityId = :cityId")
    fun fetchByCityId(cityId: Int): ForecastApiResponse?
}