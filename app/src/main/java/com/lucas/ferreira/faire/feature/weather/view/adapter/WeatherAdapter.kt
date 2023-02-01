package com.lucas.ferreira.faire.feature.weather.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucas.ferreira.faire.R
import com.lucas.ferreira.faire.feature.weather.common.extension.getDayName
import com.lucas.ferreira.faire.feature.weather.common.extension.loadIcon
import com.lucas.ferreira.faire.feature.weather.common.extension.toDecimalString
import com.lucas.ferreira.faire.feature.weather.model.ConsolidatedWeather
import kotlinx.android.synthetic.main.item_weather.view.*

class WeatherAdapter(
    private val weather: MutableList<ConsolidatedWeather>,
    private val context: Context
) : RecyclerView.Adapter<WeatherAdapter.WeatherListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.item_weather,
            parent,
            false
        )
        return WeatherListViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherListViewHolder, position: Int) {
        holder.bindView(weather[position])
    }

    override fun getItemCount(): Int = weather.size

    inner class WeatherListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(weather: ConsolidatedWeather) {
            itemView.tvWeatherState.text = weather.weatherStateName
            itemView.tvDay.text = weather.applicableDate.getDayName()
            itemView.tvTemp.text = weather.theTemp.toDecimalString(
                context, R.string.label_temperature
            )
            loadIcon(context, iconState = weather.weatherStateAbbr, itemView.icWeather)
        }
    }
}