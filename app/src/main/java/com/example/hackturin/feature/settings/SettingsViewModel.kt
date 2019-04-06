package com.example.hackturin.feature.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {
    val currentRange = MutableLiveData<Int>()
    val isTripModeOn = MutableLiveData<Boolean>()
    val isHistoryRecorded = MutableLiveData<Boolean>()


    fun isHistoryChanged(isHistoryOn : Boolean){
        isTripModeOn.value = isHistoryOn
    }

    fun isTripModeChanged(isModeOn : Boolean){
        isTripModeOn.value = isModeOn
    }

    fun currentRangeChanged(int: Int) {
        currentRange.value = int
    }
}
