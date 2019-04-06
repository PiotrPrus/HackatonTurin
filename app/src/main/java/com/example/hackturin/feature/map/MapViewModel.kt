package com.example.hackturin.feature.map

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.hackturin.data.model.GeoItem
import com.example.hackturin.data.repository.GeoFenceRepository
import com.example.hackturin.utils.addTo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MapViewModel(private val geoFenceRepository: GeoFenceRepository) : ViewModel() {

    private val disposable = CompositeDisposable()

    private fun loadGeoFences(lat: Double, long: Double, radius: Int) {
        geoFenceRepository.getGeoFences(lat, long, radius)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ showResultOnMap(it) },
                { Log.d("MapViewModel", it.toString()) })
            .addTo(disposable)
    }

    private fun showResultOnMap(list: List<GeoItem>?) {
        list?.let {  }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}