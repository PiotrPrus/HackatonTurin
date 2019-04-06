package com.example.hackturin

import android.app.Application
import com.example.hackturin.di.dataSourceModule
import com.example.hackturin.di.networkModule
import com.example.hackturin.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WonderCityApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WonderCityApp)
            modules(networkModule, dataSourceModule, repositoryModule)
        }
    }
}