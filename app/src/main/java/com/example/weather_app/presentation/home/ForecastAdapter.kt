package com.example.weather_app.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_app.R
import com.example.weather_app.data.models.weather.WeatherApiResponse
import com.example.weather_app.databinding.ItemForecastBinding
import com.squareup.picasso.Picasso
import timber.log.Timber

class ForecastAdapter(
    private val click: (Int) -> Unit
) : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    private var items: MutableList<WeatherApiResponse> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ForecastViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_forecast,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) = holder.bind(position)

    fun update(list: List<WeatherApiResponse>) {
        items = list.toMutableList()
        notifyDataSetChanged()
    }

    inner class ForecastViewHolder(var binding: ItemForecastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val forecast = items[position]
            binding.forecast = forecast
            binding.imageViewIcon.setImageResource(forecast.weather[0].getImageResource())
            binding.root.setOnClickListener { click.invoke(forecast.id) }
        }
    }
}