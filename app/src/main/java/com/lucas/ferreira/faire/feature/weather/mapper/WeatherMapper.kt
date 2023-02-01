package com.lucas.ferreira.faire.feature.weather.mapper

import com.lucas.ferreira.faire.feature.weather.model.Weather
import com.lucas.ferreira.faire.feature.weather.model.response.WeatherResponse
import com.lucas.ferreira.faire.feature.weather.view.item.TodayInfo
import com.lucas.ferreira.faire.feature.weather.view.item.TomorrowInfo
import com.lucas.ferreira.faire.feature.weather.view.item.WeatherInfo

fun WeatherResponse.toModel() =
    Weather(
        consolidatedWeather = this.consolidatedWeather,
        title = this.title
    )

fun Weather.toInfo() =
    WeatherInfo(
        title = this.title,
        consolidatedWeather = this.consolidatedWeather,
        todayInfo = with(this.consolidatedWeather[0]) {
            TodayInfo(
                theTemp,
                weatherStateAbbr,
                weatherStateName,
                minTemp,
                maxTemp
            )
        },
        tomorrowInfo = with(this.consolidatedWeather[1]) {
            TomorrowInfo(
                theTemp,
                weatherStateAbbr
            )
        }
    )
