package com.lucas.ferreira.faire.feature.weather.common.extension

import com.lucas.ferreira.faire.feature.weather.common.feedback.data.Feedback

fun Feedback.Builder.networkError() = this
    .setTitle("")
    .setMessage("")
    .build()

fun Feedback.Builder.genericError() = this
    .setTitle("")
    .setMessage("")
    .build()