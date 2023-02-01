package com.lucas.ferreira.faire.feature.weather.core

import android.content.Context

class StandardConnectivityManager(
    private val context: Context
) : ConnectivityManager {

    override fun isConnectionAvailable(): Boolean {
        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as android.net.ConnectivityManager
        return connectivityManager.activeNetworkInfo?.isConnected ?: false
    }
}