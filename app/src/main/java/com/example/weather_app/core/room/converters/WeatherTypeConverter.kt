package com.example.weather_app.core.room.converters

import androidx.room.TypeConverter
import com.example.weather_app.data.models.weather.Weather
import com.google.gson.Gson

class WeatherTypeConverter {
    @TypeConverter
    fun fromWeather(weather: Weather): String = Gson().toJson(weather)

    @TypeConverter
    fun toWeather(json: String): Weather = Gson().fromJson(json, Weather::class.java)

    @TypeConverter
    fun fromWeatherList(weatherList: List<Weather>): String = Gson().toJson(weatherList)

    @TypeConverter
    fun toWeatherList(json: String): List<Weather> =
        Gson().fromJson(json, Array<Weather>::class.java).toMutableList()
}