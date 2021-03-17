package com.example.weather_app.data.datasources.remote

import com.example.weather_app.BuildConfig
import com.example.weather_app.data.models.forecast.ForecastApiResponse
import com.example.weather_app.data.models.weather.WeatherApiResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("data/2.5/weather")
    fun fetchWeatherByCityName(
        @Query("q") cityName: String,
        @Query("units") unitSystem: String = "metric",
        @Query("appid") appId: String = BuildConfig.OPEN_WEATHER_API_KEY
    ): Observable<WeatherApiResponse>

    @GET("data/2.5/weather")
    fun fetchWeatherByCityId(
        @Query("id") cityId: Int,
        @Query("units") unitSystem: String = "metric",
        @Query("appid") appId: String = BuildConfig.OPEN_WEATHER_API_KEY
    ): Observable<WeatherApiResponse>

    @GET("data/2.5/forecast")
    fun fetchForecastByCityId(
        @Query("id") cityId: Int,
        @Query("units") unitSystem: String = "metric",
        @Query("appid") appId: String = BuildConfig.OPEN_WEATHER_API_KEY
    ): Observable<ForecastApiResponse>

}