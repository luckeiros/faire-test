package com.lucas.ferreira.faire.feature.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucas.ferreira.faire.feature.weather.common.extension.emit
import com.lucas.ferreira.faire.feature.weather.common.extension.safeLaunch
import com.lucas.ferreira.faire.feature.weather.core.ConnectivityManager
import com.lucas.ferreira.faire.feature.weather.mapper.toInfo
import com.lucas.ferreira.faire.feature.weather.repository.WeatherRepository
import com.lucas.ferreira.faire.feature.weather.viewstate.WeatherViewState
import kotlinx.coroutines.delay

class WeatherViewModel constructor(
    private val repository: WeatherRepository,
    private val connectivityManager: ConnectivityManager
) : ViewModel() {

    private val mutableState = MutableLiveData<WeatherViewState>()
    val viewState: LiveData<WeatherViewState> = mutableState

    private suspend fun getWeather() {
        val weather = repository.getWeather()
        mutableState.emit(WeatherViewState.Success(weather.toInfo()))
    }

    fun loadWeather() = safeLaunch(::handleError) {
        mutableState.emit(WeatherViewState.Loading)
        getWeather()
    }

    private fun handleError(error: Throwable) {
        if (connectivityManager.isConnectionAvailable().not()) {
            mutableState.emit(WeatherViewState.NetworkError(error))
        } else {
            mutableState.emit(WeatherViewState.Error)
        }
    }
}
