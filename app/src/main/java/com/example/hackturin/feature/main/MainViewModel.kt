package com.example.hackturin.feature.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.hackturin.data.model.GeoItem
import com.example.hackturin.data.repository.GeoFenceRepository
import com.example.hackturin.utils.addTo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val geoFenceRepository: GeoFenceRepository) : ViewModel() {

    private val disposable = CompositeDisposable()
    private var lastGeoItem: GeoItem? = null

    fun getGeoDataByLocation(lat: Double, long: Double) {
        geoFenceRepository.loadNearestAttraction(lat, long)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result -> showNotification(result) },
                { Log.d("AAAA", "Error occured fetching one attraction $it") })
            .addTo(disposable)
    }

    private fun showNotification(result: GeoItem?) {
        if (result != null && result != lastGeoItem){
            Log.d("AAAA", "Show notification for item: $result")
            lastGeoItem = result
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}