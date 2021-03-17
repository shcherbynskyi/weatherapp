package com.example.weather_app.data.models.forecast

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weather_app.data.models.weather.DailyForecast
import com.google.gson.annotations.SerializedName

@Entity(tableName = "forecast")
data class ForecastApiResponse(
    @PrimaryKey val id: Int,
    @SerializedName("city") @Embedded val city: City,
    @SerializedName("cod") val cod: Int,
    @SerializedName("message") val message: Double,
    @SerializedName("cnt") val cnt: Int,
    @SerializedName("list") val dailyForecast: List<DailyForecast>
)