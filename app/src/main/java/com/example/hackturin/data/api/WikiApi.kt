package com.example.hackturin.data.api

import com.example.hackturin.data.model.WikiJson
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WikiApi {

    @GET("api.php?action=query&prop=extracts")
    fun getInfo(
        @Query("titles") attraction: String,
        @Query("explaintext") explain: Int = 1,
        @Query("exsectionformat") format: String = "wiki",
        @Query("format") formatJson: String = "json"
    ): Single<WikiJson>
}