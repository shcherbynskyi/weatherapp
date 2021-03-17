package com.example.weather_app.core.room.converters

import androidx.room.TypeConverter
import com.example.weather_app.data.models.weather.Sys
import com.google.gson.Gson

class SysConverter {
    @TypeConverter
    fun fromSys(sys: Sys): String = Gson().toJson(sys)

    @TypeConverter
    fun toCoordinates(json: String): Sys = Gson().fromJson(json, Sys::class.java)
}