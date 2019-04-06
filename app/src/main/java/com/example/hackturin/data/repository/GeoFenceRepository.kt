package com.example.hackturin.data.repository

import com.example.hackturin.data.datasource.GeoFenceDataSource

class GeoFenceRepository(private val geoFenceDataSource: GeoFenceDataSource) {
    fun getGeoFences(lat: Double, long: Double, radius: Int) =
            geoFenceDataSource.getGeoFences(lat, long, radius)
}