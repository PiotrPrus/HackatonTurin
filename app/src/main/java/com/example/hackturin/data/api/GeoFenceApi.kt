package com.example.hackturin.data.api

import com.example.hackturin.data.model.Geometries
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface GeoFenceApi {

    @GET("proximity.json?layer_ids=4711&app_id=6c7lKmI7qmYddd9WSt6h&app_code=DArHjs-hVFw-mxwCfec9oA")
    fun getGeofencesByRadius(@Query("proximity")params: String,
                             @Query("key_attribute") name: String = "NAME"): Single<Geometries>
}