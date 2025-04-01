package com.example.news.newsFeatures.data.remote

import com.example.news.newsFeatures.data.remote.dto.ArticleDto
import com.example.news.newsFeatures.data.remote.dto.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): NewsDto
}