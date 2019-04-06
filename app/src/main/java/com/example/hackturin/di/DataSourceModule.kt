package com.example.hackturin.di

import com.example.hackturin.data.datasource.GeoFenceDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single { GeoFenceDataSource(get()) }
}