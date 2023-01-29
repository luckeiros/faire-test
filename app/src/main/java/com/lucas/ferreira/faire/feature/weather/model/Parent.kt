package com.lucas.ferreira.faire.feature.weather.model

import com.google.gson.annotations.SerializedName

data class Parent(
    @SerializedName("location_type")
    val locationType: String,
    @SerializedName("latt_long")
    val lattLong: String,
    val title: String,
    val woeid: Int
)
