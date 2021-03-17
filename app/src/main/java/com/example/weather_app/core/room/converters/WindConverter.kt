package com.example.weather_app.core.room.converters

import androidx.room.TypeConverter
import com.example.weather_app.data.models.weather.Wind
import com.google.gson.Gson

class WindConverter {
    @TypeConverter
    fun fromWind(wind: Wind): String = Gson().toJson(wind)

    @TypeConverter
    fun toWind(json: String): Wind = Gson().fromJson(json, Wind::class.java)
}