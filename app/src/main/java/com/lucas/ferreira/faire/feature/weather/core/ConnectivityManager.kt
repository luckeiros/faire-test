package com.lucas.ferreira.faire.feature.weather.core

interface ConnectivityManager {
    fun isConnectionAvailable(): Boolean
}