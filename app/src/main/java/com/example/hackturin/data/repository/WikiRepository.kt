package com.example.hackturin.data.repository

import com.example.hackturin.data.datasource.WikiDataSource

class WikiRepository(private val wikiDataSource: WikiDataSource) {
    fun getArticle(titles: String) = wikiDataSource.getInfo(titles)
}