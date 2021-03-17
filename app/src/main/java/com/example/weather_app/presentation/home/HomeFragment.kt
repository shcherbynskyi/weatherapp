package com.example.weather_app.presentation.home

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather_app.R
import com.example.weather_app.core.extensions.toast
import com.example.weather_app.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var forecastAdapter: ForecastAdapter
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        state: Bundle?
    ): View? {

        forecastAdapter = ForecastAdapter {
            val bundle = bundleOf(CITY_ID_KEY to it)
            findNavController().navigate(R.id.action_homeFragment_to_weatherDetailFragment, bundle)
        }

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )

        binding.recyclerViewForecast.apply {
            adapter = forecastAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.weatherList.observe(viewLifecycleOwner) { forecastAdapter.update(it) }
        viewModel.error.observe(viewLifecycleOwner) { error ->
            error.localizedMessage?.let {
                context?.toast(
                    it
                )
            }
        }

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAddCityWeather.setOnClickListener {
            AlertDialog.Builder(context)
                .setView(R.layout.dialog_add_city)
                .setTitle("Choose a city to observe the weather")
                .setPositiveButton("Ok") { dialogInterface, _ ->
                    val dialog = dialogInterface as Dialog
                    val text =
                        dialog.findViewById<EditText>(R.id.edit_text_city_name).text.toString()
                    viewModel.fetchWeatherForCity(text)
                }
                .setNegativeButton("Cancel") { dialogInterface, _ ->
                    dialogInterface.cancel()
                }
                .create()
                .show()
        }
    }

    companion object {
        const val CITY_ID_KEY = "cityId"
    }
}