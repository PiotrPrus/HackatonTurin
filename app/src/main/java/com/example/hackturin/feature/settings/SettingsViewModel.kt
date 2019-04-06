package com.example.hackturin.feature.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;

class SettingsViewModel : ViewModel() {
    val currentRange = MutableLiveData<Int>()
    // TODO: Implement the ViewModel

    fun currentRangeCHanged(int: Int) {
        currentRange.value = int
    }
}
