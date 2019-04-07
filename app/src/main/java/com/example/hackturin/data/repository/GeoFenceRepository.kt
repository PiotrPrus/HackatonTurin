package com.example.hackturin.data.repository

import com.example.hackturin.data.datasource.GeoFenceDataSource
import com.example.hackturin.data.model.GeoItem
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GeoFenceRepository(private val geoFenceDataSource: GeoFenceDataSource) {
    fun getGeoFences(lat: Double, long: Double, radius: Int) =
        geoFenceDataSource.getGeoFences(lat, long, radius)

    fun loadNearestAttraction(lat: Double, long: Double): Single<GeoItem> {
        return geoFenceDataSource.getGeoFences(lat, long, 50)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map { list -> list.geometries[0] }

    }
}