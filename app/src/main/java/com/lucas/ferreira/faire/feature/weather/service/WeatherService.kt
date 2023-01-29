package com.lucas.ferreira.faire.feature.weather.service

import com.lucas.ferreira.faire.feature.weather.model.response.WeatherResponse
import com.lucas.ferreira.faire.feature.weather.path.Path.WEATHER_PATH
import retrofit2.http.GET

interface WeatherService {
    @GET(WEATHER_PATH)
    suspend fun getWeather(): WeatherResponse
}
