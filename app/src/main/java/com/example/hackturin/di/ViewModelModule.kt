package com.example.hackturin.di

import com.example.hackturin.feature.map.MapViewModel
import com.example.hackturin.feature.map.SharedViewModel
import com.example.hackturin.feature.settings.SettingsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SettingsViewModel() }
    viewModel { MapViewModel(get()) }
    viewModel { SharedViewModel() }
}