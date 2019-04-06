package com.example.hackturin.feature.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {
    val currentLocation = MutableLiveData<Pair<Double, Double>>()
}