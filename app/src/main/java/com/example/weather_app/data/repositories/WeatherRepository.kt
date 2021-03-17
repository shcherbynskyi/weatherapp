package com.example.weather_app.data.repositories

import com.example.weather_app.data.datasources.cache.WeatherDao
import com.example.weather_app.data.datasources.remote.OpenWeatherApi
import com.example.weather_app.data.models.forecast.ForecastApiResponse
import com.example.weather_app.data.models.weather.WeatherApiResponse
import io.reactivex.Observable
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private var api: OpenWeatherApi,
    private var dao: WeatherDao
) {

    fun fetchWeatherFromRemoteByCityName(cityName: String): Observable<WeatherApiResponse> =
        api.fetchWeatherByCityName(cityName)

    fun fetchWeatherFromRemoteByCityId(cityId: Int): Observable<WeatherApiResponse> =
        api.fetchWeatherByCityId(cityId)

    fun insertWeatherIntoCache(weather: WeatherApiResponse) = dao.insert(weather)

    fun fetchAllWeatherFromCache(): List<WeatherApiResponse> =
        dao.fetchAll()

    fun fetchWeatherFromCacheByCityName(cityName: String): WeatherApiResponse? =
        dao.fetchByCityName(cityName)

    fun fetchWeatherFromCacheByCityId(cityId: Int): WeatherApiResponse? =
        dao.fetchByCityId(cityId)
}