package com.lucas.ferreira.faire.feature.weather.model.response

import com.google.gson.annotations.SerializedName
import com.lucas.ferreira.faire.feature.weather.model.ConsolidatedWeather

data class WeatherResponse(
    @SerializedName("consolidated_weather")
    val consolidatedWeather: List<ConsolidatedWeather>,
    val title: String
)
