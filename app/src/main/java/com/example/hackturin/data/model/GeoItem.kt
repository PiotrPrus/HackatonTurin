package com.example.hackturin.data.model

import com.google.gson.annotations.SerializedName

data class GeoItem (

    @SerializedName("attributes") val attributes : Attributes,
    @SerializedName("distance") val distance : Double,
    @SerializedName("nearestLat") val nearestLat : Double,
    @SerializedName("nearestLon") val nearestLon : Double,
    @SerializedName("layerId") val layerId : Int,
    @SerializedName("geometry") val geometry : String
)

data class Geometries(
    @SerializedName("geometries") val geometries: List<GeoItem>
)