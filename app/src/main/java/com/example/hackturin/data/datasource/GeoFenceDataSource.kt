package com.example.hackturin.data.datasource

import com.example.hackturin.data.api.GeoFenceApi
import com.example.hackturin.data.model.GeoItem
import com.example.hackturin.data.model.Geometries
import io.reactivex.Single

class GeoFenceDataSource(private val api: GeoFenceApi) {
    fun getGeoFences(lat: Double, long: Double, radius: Int): Single<Geometries> {
        val params = "$lat, $long, $radius"
        return api.getGeofencesByRadius(params)
    }

}