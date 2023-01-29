package com.lucas.ferreira.faire.feature.weather.model

import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("crawl_rate")
    val crawlRate: Int,
    val title: String,
    val slug: String,
    val url: String
)
