package com.example.weather_app.core.room.converters

import androidx.room.TypeConverter
import com.example.weather_app.data.models.weather.DailyForecast
import com.google.gson.Gson

class DailyForecastConverter {

    @TypeConverter
    fun fromDailyForecast(dailyForecast: DailyForecast): String = Gson().toJson(dailyForecast)

    @TypeConverter
    fun toDailyForecast(json: String): DailyForecast =
        Gson().fromJson(json, DailyForecast::class.java)

    @TypeConverter
    fun fromDailyForecastList(dailyForecastList: List<DailyForecast>): String =
        Gson().toJson(dailyForecastList)

    @TypeConverter
    fun toDailyForecastList(json: String): List<DailyForecast> =
        Gson().fromJson(json, Array<DailyForecast>::class.java).toMutableList()
}