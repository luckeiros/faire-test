package com.lucas.ferreira.faire.feature.weather.model

data class Weather(
    val consolidatedWeather: List<ConsolidatedWeather>,
    val title: String
)
