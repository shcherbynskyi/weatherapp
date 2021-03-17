package com.example.weather_app.core.room.converters

import androidx.room.TypeConverter
import com.example.weather_app.data.models.weather.Main
import com.google.gson.Gson

class MainConverter {
    @TypeConverter
    fun fromMain(main: Main): String = Gson().toJson(main)

    @TypeConverter
    fun toMain(json: String): Main = Gson().fromJson(json, Main::class.java)
}