package com.example.weather_app.data.models.forecast

import com.example.weather_app.data.models.weather.Coordinates
import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("id") val cityId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("coord") val coordinates: Coordinates,
    @SerializedName("country") val country: String,
    @SerializedName("population") val population: Int,
    @SerializedName("timezone") val timezone: Int
)