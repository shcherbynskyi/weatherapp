package com.example.weather_app.presentation.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather_app.data.models.forecast.ForecastApiResponse
import com.example.weather_app.data.models.weather.WeatherApiResponse
import com.example.weather_app.data.repositories.ForecastRepository
import com.example.weather_app.data.repositories.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class WeatherDetailViewModel @Inject constructor(
    private var weatherRepository: WeatherRepository,
    private var forecastRepository: ForecastRepository
) : ViewModel() {

    val weather: MutableLiveData<WeatherApiResponse> = MutableLiveData()
    val forecast: MutableLiveData<ForecastApiResponse> = MutableLiveData()
    val error: MutableLiveData<Throwable> = MutableLiveData()

    fun fetchWeatherAndForecastForCity(cityId: Int) {
        val disposable = weatherRepository.fetchWeatherFromRemoteByCityId(cityId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                weatherRepository.insertWeatherIntoCache(it)
                weather.postValue(it)
            }, {
                weather.postValue(weatherRepository.fetchWeatherFromCacheByCityId(cityId))
                error.postValue(it)
            })
        val forecastDisposable = forecastRepository.fetchForecastFromRemoteByCityId(cityId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                forecastRepository.insertWeatherIntoCache(it)
                forecast.postValue(it)
            }, {
                forecast.postValue(forecastRepository.fetchForecastFromCacheByCityId(cityId))
                error.postValue(it)
            })
    }

}