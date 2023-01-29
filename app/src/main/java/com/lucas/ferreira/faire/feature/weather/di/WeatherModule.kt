package com.lucas.ferreira.faire.feature.weather.di

import com.lucas.ferreira.faire.feature.weather.repository.WeatherRepository
import com.lucas.ferreira.faire.feature.weather.repository.WeatherRepositoryImpl
import com.lucas.ferreira.faire.feature.weather.viewmodel.WeatherViewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val weatherModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory<WeatherRepository> { WeatherRepositoryImpl(get()) }

    factory { WeatherViewModel(get()) }

}

private const val BASE_URL = "https://cdn.faire.com/"