package com.lucas.ferreira.faire.feature.weather.viewstate

import com.lucas.ferreira.faire.feature.weather.model.Weather

sealed class WeatherViewState {
    object Loading : WeatherViewState()
    object Error : WeatherViewState()
    data class NetworkError(val throwable: Throwable) : WeatherViewState()
    data class Success(val weather: Weather) : WeatherViewState()
}
