package com.example.hackturin.feature.map

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hackturin.R
import com.example.hackturin.data.model.GeoItem
import com.example.hackturin.data.model.WikiItem
import com.example.hackturin.data.model.WikiJson
import com.example.hackturin.data.repository.GeoFenceRepository
import com.example.hackturin.data.repository.WikiRepository
import com.example.hackturin.utils.addTo
import com.here.android.mpa.common.GeoCoordinate
import com.here.android.mpa.common.Image
import com.here.android.mpa.mapping.MapMarker
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MapViewModel(
    private val geoFenceRepository: GeoFenceRepository,
    private val wikiRepository: WikiRepository
) : ViewModel() {

    private val disposable = CompositeDisposable()
    val mapMakers = MutableLiveData<List<MapMarker>>()
    val wikiText = MutableLiveData<String>()
    val wikiTextTitle = MutableLiveData<String>()
    val wikiSubtitle = MutableLiveData<String>()

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

    fun getWikiText(attractionName: String) {
        wikiRepository.getArticle(attractionName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                showWikiResult(it)
            },
                { Log.d("MapViewModel", it.toString()) })
            .addTo(disposable)
    }

    private fun showWikiResult(item: WikiJson) {
        Log.d("AAAA", "Wiki text: ${item}")
        item?.let {
            for (value in item.query.pages.values) {
                wikiTextTitle.value = value.title
                wikiText.value = value.extract
                wikiSubtitle.value = value.extract.substring(0, 30)
            }
        }
    }

    private fun showResultOnMap(list: List<GeoItem>?) {
        list?.let {
            val mapList = mutableListOf<MapMarker>()
//            val image = Image()
//            try {
//                image.setImageResource(R.drawable.ic_launcher_foreground)
//            } catch (e: Exception){
//                Log.d("MapViewModel", e.localizedMessage)
//            }
            for (item in list) {
                val marker = MapMarker(GeoCoordinate(item.nearestLat, item.nearestLon))
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