package com.example.weather_app.core.room.converters

import androidx.room.TypeConverter
import com.example.weather_app.data.models.weather.Coordinates
import com.google.gson.Gson

class CoordinatesConverter {
    @TypeConverter
    fun fromCoordinates(coordinates: Coordinates): String = Gson().toJson(coordinates)

    @TypeConverter
    fun toCoordinates(json: String): Coordinates = Gson().fromJson(json, Coordinates::class.java)
}