package com.example.news.newsFeatures.domain.repository

import com.example.news.core.Resource
import com.example.news.newsFeatures.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(): Flow<Resource<List<Article>>>

    suspend fun refreshNews(country: String, apiKey: String)
}