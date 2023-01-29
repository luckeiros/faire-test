package com.lucas.ferreira.faire.feature.weather.model

data class Weather(
    val consolidatedWeather: List<ConsolidatedWeather>,
    val sunrise: String,
    val sunset: String,
    val timezoneName: String,
    val locationType: String,
    val lattLong: String,
    val time: String,
    val parent: Parent,
    val sources: List<Source>,
    val title: String,
    val woeid: Int,
    val timezone: String
)
