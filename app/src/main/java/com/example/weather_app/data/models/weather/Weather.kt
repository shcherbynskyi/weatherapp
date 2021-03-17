package com.example.weather_app.data.models.weather

import com.example.weather_app.R
import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
) {
    fun getImageResource(): Int {
        return when (main) {
            "Thunderstorm" -> R.drawable.ic_thunder
            "Drizzle", "Rain" -> R.drawable.ic_raining
            "Snow" -> R.drawable.ic_snow
            "Clear" -> R.drawable.ic_sunny
            "Clouds" -> R.drawable.ic_clouds
            else -> R.drawable.ic_sunny
        }
    }
}