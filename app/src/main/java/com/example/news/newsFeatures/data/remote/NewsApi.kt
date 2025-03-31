package com.example.news.newsFeatures.data.remote


import com.example.news.newsFeatures.data.remote.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("/v2/top-headlines?")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<NewsResponseDto>
}