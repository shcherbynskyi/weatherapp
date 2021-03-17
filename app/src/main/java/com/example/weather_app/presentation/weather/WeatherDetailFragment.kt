package com.example.weather_app.presentation.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.weather_app.R
import com.example.weather_app.core.extensions.toast
import com.example.weather_app.databinding.FragmentWeatherDetailBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherDetailFragment : Fragment() {

    private lateinit var binding: FragmentWeatherDetailBinding
    private lateinit var viewModel: WeatherDetailViewModel
    private var dailyForecastAdapter: DailyForecastAdapter = DailyForecastAdapter()

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        state: Bundle?
    ): View? {

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        viewModel = ViewModelProvider(this).get(WeatherDetailViewModel::class.java)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_weather_detail,
            container,
            false
        )

        binding.recyclerViewDailyForecast.adapter = dailyForecastAdapter

        arguments?.getInt(CITY_ID_KEY)?.let { viewModel.fetchWeatherAndForecastForCity(it) }
        viewModel.weather.observe(viewLifecycleOwner) {
            binding.weather = it
            binding.imageViewCityName.setImageResource(it.weather[0].getImageResource())
        }
        viewModel.forecast.observe(viewLifecycleOwner) { dailyForecastAdapter.update(it.dailyForecast) }
        viewModel.error.observe(viewLifecycleOwner) { error ->
            error.localizedMessage?.let {
                context?.toast(
                    it
                )
            }
        }

        return binding.root
    }

    companion object {
        const val CITY_ID_KEY = "cityId"
    }
}