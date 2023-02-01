package com.lucas.ferreira.faire.feature.weather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lucas.ferreira.faire.R
import com.lucas.ferreira.faire.databinding.FragmentWeatherBinding
import com.lucas.ferreira.faire.feature.weather.common.extension.*
import com.lucas.ferreira.faire.feature.weather.common.feedback.data.Feedback
import com.lucas.ferreira.faire.feature.weather.view.adapter.WeatherAdapter
import com.lucas.ferreira.faire.feature.weather.view.item.WeatherInfo
import com.lucas.ferreira.faire.feature.weather.viewmodel.WeatherViewModel
import com.lucas.ferreira.faire.feature.weather.viewstate.WeatherViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherFragment : Fragment() {

    private val weatherViewModel: WeatherViewModel by viewModel()

    private lateinit var binding: FragmentWeatherBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherViewModel.loadWeather()
        observeStates()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun observeStates() {
        observe(weatherViewModel.viewState) { state ->
            when (state) {
                is WeatherViewState.Loading -> showLoading()
                is WeatherViewState.Error -> showError()
                is WeatherViewState.NetworkError -> showNetworkError()
                is WeatherViewState.Success -> showWeatherInfo(state.weatherInfo)
            }
        }
    }

    private fun setCity(weatherInfo: WeatherInfo) {
        binding.tvCity.text = weatherInfo.title
    }

    private fun setupTodayInfo(weatherInfo: WeatherInfo) {
        with(binding) {
            tvTodayTemp.text = weatherInfo.todayInfo.temp.toDecimalString(
                requireContext(),
                R.string.label_temperature
            )

            tvMinTemp.text = weatherInfo.todayInfo.minTemp.toDecimalString(
                requireContext(),
                R.string.label_temperature_min
            )

            tvMaxTemp.text = weatherInfo.todayInfo.maxTemp.toDecimalString(
                requireContext(),
                R.string.label_temperature_min
            )

            tvTodayWeatherState.text = weatherInfo.todayInfo.weatherState
        }

        loadIcon(requireContext(), weatherInfo.todayInfo.icon, binding.icTodayWeather)
    }

    private fun setupTomorrowInfo(weatherInfo: WeatherInfo) {
        binding.tvTomorrowTemp.text = weatherInfo.tomorrowInfo.temp.toDecimalString(
            requireContext(),
            R.string.label_temperature
        )

        loadIcon(requireContext(), weatherInfo.tomorrowInfo.icon, binding.icTomorrowWeather)
    }

    private fun setRecyclerViewData(weatherInfo: WeatherInfo) {
        binding.rvWeather.adapter =
            WeatherAdapter(weatherInfo.consolidatedWeather.toMutableList(), requireContext())
    }

    private fun showLoading() {
        binding.groupContent.turnGone()
        binding.pbWeather.turnVisible()
    }

    private fun showWeatherInfo(weatherInfo: WeatherInfo) {
        binding.pbWeather.turnGone()
        binding.groupContent.turnVisible()

        setCity(weatherInfo)
        setupTodayInfo(weatherInfo)
        setupTomorrowInfo(weatherInfo)
        setRecyclerViewData(weatherInfo)
    }

    private fun showError() {
        binding.pbWeather.turnGone()
        binding.feedbackView.setFeedback(Feedback.Builder().genericError(requireContext()))
        binding.feedbackView.turnVisible()
    }

    private fun showNetworkError() {
        binding.pbWeather.turnGone()
        binding.feedbackView.setFeedback(Feedback.Builder().networkError(requireContext()))
        binding.feedbackView.turnVisible()
    }
}
