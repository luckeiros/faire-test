package com.lucas.ferreira.faire.feature.weather

import android.app.Application
import com.lucas.ferreira.faire.feature.weather.di.weatherModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(baseContext)
            modules(weatherModule)
        }
    }
}
