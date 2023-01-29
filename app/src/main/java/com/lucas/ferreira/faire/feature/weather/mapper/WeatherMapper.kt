package com.lucas.ferreira.faire.feature.weather.mapper

import com.lucas.ferreira.faire.feature.weather.model.Weather
import com.lucas.ferreira.faire.feature.weather.model.response.WeatherResponse

fun WeatherResponse.toModel() =
    Weather(
        consolidatedWeather = this.consolidatedWeather,
        sunrise = this.sunrise,
        sunset = this.sunset,
        timezoneName = this.timezoneName,
        locationType = this.locationType,
        lattLong = this.lattLong,
        time = this.time,
        parent = this.parent,
        sources = this.sources,
        title = this.title,
        woeid = this.woeid,
        timezone = this.timezone
    )