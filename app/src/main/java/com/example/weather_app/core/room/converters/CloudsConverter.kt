package com.example.weather_app.core.room.converters

import androidx.room.TypeConverter
import com.example.weather_app.data.models.weather.Clouds
import com.google.gson.Gson

class CloudsConverter {

    @TypeConverter
    fun fromClouds(clouds: Clouds): String = Gson().toJson(clouds)

    @TypeConverter
    fun toClouds(json: String): Clouds = Gson().fromJson(json, Clouds::class.java)
}