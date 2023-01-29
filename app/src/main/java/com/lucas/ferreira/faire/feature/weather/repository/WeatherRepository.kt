package com.lucas.ferreira.faire.feature.weather.repository

import com.lucas.ferreira.faire.feature.weather.model.Weather

interface WeatherRepository {
    suspend fun getWeather(): Weather
}