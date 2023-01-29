package com.lucas.ferreira.faire.feature.weather.viewmodel

import android.accounts.NetworkErrorException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucas.ferreira.faire.feature.weather.common.extension.emit
import com.lucas.ferreira.faire.feature.weather.common.extension.safeLaunch
import com.lucas.ferreira.faire.feature.weather.repository.WeatherRepository
import com.lucas.ferreira.faire.feature.weather.viewstate.WeatherViewState

class WeatherViewModel constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    val viewState: LiveData<WeatherViewState> = MutableLiveData()

    private suspend fun getWeather() {
        val weather = repository.getWeather()
        viewState.emit(WeatherViewState.Success(weather))
    }

    fun loadWeather() = safeLaunch(::handleError) {
        viewState.emit(WeatherViewState.Loading)
        getWeather()
    }

    private fun handleError(error: Throwable) {
        if (error is NetworkErrorException) {
            viewState.emit(WeatherViewState.NetworkError(error))
        } else {
            viewState.emit(WeatherViewState.Error)
        }
    }
}
