package com.lucas.ferreira.faire.feature.weather.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lucas.ferreira.faire.R
import com.lucas.ferreira.faire.feature.weather.common.extension.openFragment

class WeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        openWeatherFragment()
    }

    private fun openWeatherFragment() {
        val container = R.id.container

        openFragment(WeatherFragment(), container, true)
    }
}
