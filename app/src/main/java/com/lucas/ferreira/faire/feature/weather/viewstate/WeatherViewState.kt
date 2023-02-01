package com.lucas.ferreira.faire.feature.weather.viewstate

import com.lucas.ferreira.faire.feature.weather.view.item.WeatherInfo

sealed class WeatherViewState {
    object Loading : WeatherViewState()
    object Error : WeatherViewState()
    data class NetworkError(val throwable: Throwable) : WeatherViewState()
    data class Success(val weatherInfo: WeatherInfo) : WeatherViewState()
}
