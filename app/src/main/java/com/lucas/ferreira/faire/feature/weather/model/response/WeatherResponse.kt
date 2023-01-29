package com.lucas.ferreira.faire.feature.weather.model.response

import com.google.gson.annotations.SerializedName
import com.lucas.ferreira.faire.feature.weather.model.ConsolidatedWeather
import com.lucas.ferreira.faire.feature.weather.model.Parent
import com.lucas.ferreira.faire.feature.weather.model.Source

data class WeatherResponse(
    @SerializedName("consolidated_weather")
    val consolidatedWeather: List<ConsolidatedWeather>,
    @SerializedName("sun_rise")
    val sunrise: String,
    @SerializedName("sun_set")
    val sunset: String,
    @SerializedName("timezone_name")
    val timezoneName: String,
    @SerializedName("location_type")
    val locationType: String,
    @SerializedName("latt_long")
    val lattLong: String,
    val time: String,
    val parent: Parent,
    val sources: List<Source>,
    val title: String,
    val woeid: Int,
    val timezone: String
)
