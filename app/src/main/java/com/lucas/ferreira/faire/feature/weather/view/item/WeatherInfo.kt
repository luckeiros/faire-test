package com.lucas.ferreira.faire.feature.weather.view.item

import com.lucas.ferreira.faire.feature.weather.model.ConsolidatedWeather

data class WeatherInfo(
    val title: String,
    val consolidatedWeather: List<ConsolidatedWeather>,
    val todayInfo: TodayInfo,
    val tomorrowInfo: TomorrowInfo
)
