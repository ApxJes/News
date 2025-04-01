package com.example.news.newsFeatures.data.remote.dto

import com.example.news.newsFeatures.data.local.ArticleEntity
import com.example.news.newsFeatures.domain.model.News
import com.google.gson.annotations.SerializedName

data class NewsDto(
    @SerializedName("articles")
    val articles: List<ArticleDto>,
    @SerializedName("status")
    val status: String?,
    @SerializedName("totalResults")
    val totalResults: Int?
) {
    fun toArticleEntity(): List<ArticleEntity> {
       return articles.map {
           it.toArticleEntity()
       }
    }
}