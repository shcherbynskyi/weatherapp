package com.example.weather_app.presentation.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_app.R
import com.example.weather_app.core.extensions.getDate
import com.example.weather_app.core.extensions.getTime
import com.example.weather_app.data.models.weather.DailyForecast
import com.example.weather_app.databinding.ItemDailyWeatherBinding
import timber.log.Timber

class DailyForecastAdapter : RecyclerView.Adapter<DailyForecastAdapter.ForecastViewHolder>() {

    private var items: List<DailyForecast> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder =
        ForecastViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_daily_weather,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) = holder.bind(position)

    fun update(data: List<DailyForecast>) {
        items = data
        notifyDataSetChanged()
    }

    /**
     *
     */
    inner class ForecastViewHolder(var binding: ItemDailyWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val forecast = items[position]
            binding.forecast = forecast
            binding.imageViewIcon.setImageResource(forecast.weather[0].getImageResource())
        }
    }
}