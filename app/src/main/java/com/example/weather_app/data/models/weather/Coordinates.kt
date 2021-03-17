package com.example.weather_app.data.models.weather

import com.google.gson.annotations.SerializedName

data class Coordinates(
    @SerializedName("lon") val lon: Double,
    @SerializedName("lat") val lat: Double
)