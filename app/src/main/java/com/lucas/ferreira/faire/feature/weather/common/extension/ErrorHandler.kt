package com.lucas.ferreira.faire.feature.weather.common.extension

import android.content.Context
import com.lucas.ferreira.faire.R
import com.lucas.ferreira.faire.feature.weather.common.feedback.data.Feedback

fun Feedback.Builder.networkError(context: Context) = this
    .setTitle(context.getString(R.string.title_network_error))
    .setMessage(context.getString(R.string.message_network_error))
    .build()

fun Feedback.Builder.genericError(context: Context) = this
    .setTitle(context.getString(R.string.title_generic_error))
    .setMessage(context.getString(R.string.message_generic_error))
    .build()