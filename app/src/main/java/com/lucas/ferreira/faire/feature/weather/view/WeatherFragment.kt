package com.lucas.ferreira.faire.feature.weather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.lucas.ferreira.faire.R
import com.lucas.ferreira.faire.databinding.FragmentWeatherBinding
import com.lucas.ferreira.faire.feature.weather.common.extension.genericError
import com.lucas.ferreira.faire.feature.weather.common.extension.networkError
import com.lucas.ferreira.faire.feature.weather.common.extension.observe
import com.lucas.ferreira.faire.feature.weather.common.feedback.data.Feedback
import com.lucas.ferreira.faire.feature.weather.common.feedback.view.FeedbackView
import com.lucas.ferreira.faire.feature.weather.model.Weather
import com.lucas.ferreira.faire.feature.weather.viewmodel.WeatherViewModel
import com.lucas.ferreira.faire.feature.weather.viewstate.WeatherViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherFragment : Fragment() {

    private val weatherViewModel: WeatherViewModel by viewModel()
    private var feedbackView: FeedbackView? = null
    private var tvCity: TextView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherViewModel.loadWeather()
        observeStates()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)
        feedbackView = view.findViewById(R.id.feedbackView)
        tvCity = view.findViewById(R.id.tvCity)
        return view
    }

    private fun observeStates() {
        observe(weatherViewModel.viewState) { state ->
            when (state) {
                is WeatherViewState.Loading -> showLoading()
                is WeatherViewState.Error -> showError()
                is WeatherViewState.NetworkError -> showNetworkError()
                is WeatherViewState.Success -> showWeatherInfo(state.weather)
            }
        }
    }

    private fun showWeatherInfo(weather: Weather) {
        tvCity?.text = weather.title
    }

    private fun showNetworkError() {
        feedbackView?.setFeedback(Feedback.Builder().networkError())
    }

    private fun showError() {
        feedbackView?.setFeedback(Feedback.Builder().genericError())
    }

    private fun showLoading() {
        Toast.makeText(requireContext(), "mostra o loading", Toast.LENGTH_SHORT).show()
    }
}
