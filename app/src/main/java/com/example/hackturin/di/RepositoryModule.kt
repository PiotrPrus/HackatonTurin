package com.example.hackturin.di

import com.example.hackturin.data.repository.GeoFenceRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { GeoFenceRepository(get()) }
}