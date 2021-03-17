package com.example.weather_app.data.models.weather

import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all") val all: Int
)