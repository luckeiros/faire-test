package com.lucas.ferreira.faire.feature.weather.view.item

data class TodayInfo(
    val temp: Double,
    val icon: String,
    val weatherState: String,
    val minTemp: Double,
    val maxTemp: Double
)
