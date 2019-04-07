package com.example.hackturin.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T> LifecycleOwner.observeEvent(eventLiveData: LiveData<Event<T>>?, block: (T) -> Unit) {
    eventLiveData?.observe(this, EventObserver(block))
}