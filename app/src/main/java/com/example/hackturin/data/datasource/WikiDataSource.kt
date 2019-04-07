package com.example.hackturin.data.datasource

import com.example.hackturin.data.api.WikiApi

class WikiDataSource(private val wikiApi: WikiApi) {
    fun getInfo(titles: String) = wikiApi.getInfo(titles)
}