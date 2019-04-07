package com.example.hackturin.data.model

import com.google.gson.annotations.SerializedName

data class Normalized(

    val from: String,
    val to: String
)

data class Query(
    val normalized: List<Normalized>,
    val pages: Map<Int, WikiItem>
)

data class WikiItem(
    val pageid: Int, val ns: Int, val title: String, val extract: String
)


data class WikiJson(

    val batchcomplete: String,
    val warnings: Warnings,
    val query: Query
)

data class Warnings(
    val extracts: Extracts
)


data class Extracts(
    @SerializedName("*") val extracts: String
)