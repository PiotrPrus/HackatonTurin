package com.example.hackturin.data.datasource

import com.example.hackturin.data.api.GeoFenceApi

class GeoFenceDataSource(private val api: GeoFenceApi) {
    fun getGeoFences(lat: Double, long: Double, radius: Int) =
            api.getGeofencesByRadius(lat, long, radius)
}