package com.example.news.newsFeatures.data.remote.dto

import com.example.news.newsFeatures.data.local.NewsResponseEntity
import com.google.gson.annotations.SerializedName

data class NewsResponseDto(
    @SerializedName("articles")
    val articles: List<ArticleDto>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
) {
    fun toNewsResponseEntity(): NewsResponseEntity {
        return NewsResponseEntity(
            articles = articles.map {
                it.toArticle()
            },
            status = status,
            totalResults = totalResults
        )
    }
}