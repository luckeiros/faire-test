package com.lucas.ferreira.faire.factory

import com.lucas.ferreira.faire.feature.weather.model.ConsolidatedWeather
import com.lucas.ferreira.faire.feature.weather.model.Weather

fun getWeather() =
    Weather(
        consolidatedWeather = listOf(
            setConsolidatedWeather(),
            setConsolidatedWeather()
        ),
        title = "title"
    )

fun setConsolidatedWeather() =
    ConsolidatedWeather("a", "b", "c", 0.0, 0.1, 0.2)