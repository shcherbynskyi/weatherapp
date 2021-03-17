package com.example.weather_app.core.room.converters

import androidx.room.TypeConverter
import com.example.weather_app.data.models.forecast.City
import com.google.gson.Gson

class CityConverter {

    @TypeConverter
    fun fromCity(city: City): String = Gson().toJson(city)

    @TypeConverter
    fun toCity(json: String): City = Gson().fromJson(json, City::class.java)
}