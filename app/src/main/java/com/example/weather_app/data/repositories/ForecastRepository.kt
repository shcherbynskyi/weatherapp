package com.example.weather_app.data.repositories

import com.example.weather_app.data.datasources.cache.ForecastDao
import com.example.weather_app.data.datasources.remote.OpenWeatherApi
import com.example.weather_app.data.models.forecast.ForecastApiResponse
import io.reactivex.Observable
import javax.inject.Inject

class ForecastRepository @Inject constructor(
    private var api: OpenWeatherApi,
    private var dao: ForecastDao
) {

    fun fetchForecastFromRemoteByCityId(cityId: Int): Observable<ForecastApiResponse> =
        api.fetchForecastByCityId(cityId)

    fun insertWeatherIntoCache(forecast: ForecastApiResponse) = dao.insert(forecast)

    fun fetchForecastFromCacheByCityId(cityId: Int): ForecastApiResponse? =
        dao.fetchByCityId(cityId)
}