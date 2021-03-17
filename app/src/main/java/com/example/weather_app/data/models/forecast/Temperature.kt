package com.example.weather_app.data.models.forecast

import com.google.gson.annotations.SerializedName

data class Temperature(
    @SerializedName("day") val day: Double,
    @SerializedName("min") val min: Double = 0.0,
    @SerializedName("max") val max: Double = 0.0,
    @SerializedName("night") val night: Double,
    @SerializedName("eve") val eve: Double,
    @SerializedName("morn") val morn: Double
)