package com.example.weather_app.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather_app.data.models.weather.WeatherApiResponse
import com.example.weather_app.data.repositories.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private var repository: WeatherRepository
) : ViewModel() {

    val weatherList: MutableLiveData<List<WeatherApiResponse>> = MutableLiveData()
    val error: MutableLiveData<Throwable> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        weatherList.postValue(repository.fetchAllWeatherFromCache())
    }

    fun fetchWeatherForCity(cityName: String) {
        isLoading.value = true
        val disposable = repository.fetchWeatherFromRemoteByCityName(cityName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onNext = {
                    repository.insertWeatherIntoCache(it)
                    weatherList.postValue(repository.fetchAllWeatherFromCache())
                },
                onError = {
                    error.postValue(it)
                },
                onComplete = {
                    isLoading.value = false
                }
            )
    }

}