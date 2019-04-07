package com.example.hackturin.di

import com.example.hackturin.data.repository.GeoFenceRepository
import com.example.hackturin.data.repository.WikiRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { GeoFenceRepository(get()) }
    single { WikiRepository(get()) }
}