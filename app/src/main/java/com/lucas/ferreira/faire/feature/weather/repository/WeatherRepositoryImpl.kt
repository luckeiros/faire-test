package com.lucas.ferreira.faire.feature.weather.repository

import com.lucas.ferreira.faire.feature.weather.mapper.toModel
import com.lucas.ferreira.faire.feature.weather.model.Weather
import com.lucas.ferreira.faire.feature.weather.service.WeatherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class WeatherRepositoryImpl constructor(
    private val retrofit: Retrofit
) : WeatherRepository {

    override suspend fun getWeather(): Weather =
        withContext(Dispatchers.IO) {
            val service = retrofit.create(WeatherService::class.java)
            service.getWeather().toModel()
        }

}
