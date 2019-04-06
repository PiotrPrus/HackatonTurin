package com.example.hackturin.feature.map

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hackturin.R
import com.example.hackturin.data.model.GeoItem
import com.example.hackturin.data.repository.GeoFenceRepository
import com.example.hackturin.utils.addTo
import com.here.android.mpa.common.GeoCoordinate
import com.here.android.mpa.common.Image
import com.here.android.mpa.mapping.MapMarker
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MapViewModel(private val geoFenceRepository: GeoFenceRepository) : ViewModel() {

    private val disposable = CompositeDisposable()
    val mapMakers = MutableLiveData<List<MapMarker>>()

    fun loadGeoFences(lat: Double, long: Double, radius: Int) {
        geoFenceRepository.getGeoFences(lat, long, radius)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                showResultOnMap(it.geometries)
            },
                { Log.d("MapViewModel", it.toString()) })
            .addTo(disposable)
    }

    private fun showResultOnMap(list: List<GeoItem>?) {
        Log.d("aaaa", "List fetched: $list")
        list?.let {
            val mapList = mutableListOf<MapMarker>()
//            val image = Image()
//            try {
//                image.setImageResource(R.drawable.ic_launcher_foreground)
//            } catch (e: Exception){
//                Log.d("MapViewModel", e.localizedMessage)
//            }
            for (item in list) {
                Log.d("aaaa", "One item from list: $item")
                val marker = MapMarker(GeoCoordinate(item.nearestLat, item.nearestLon))
                Log.d("aaaa", "Marker item: $marker")
                mapList.add(marker)
            }
            mapMakers.value = mapList
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}