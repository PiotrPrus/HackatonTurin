package com.example.hackturin.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Attributes (
    @SerializedName("LINK") val LINK : String,
    @SerializedName("ID") val ID : Int,
    @SerializedName("GEOMETRY_ID") val GEOMETRY_ID : Int,
    @SerializedName("NAME") val NAME : String
): Parcelable