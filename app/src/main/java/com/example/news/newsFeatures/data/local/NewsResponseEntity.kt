package com.example.news.newsFeatures.data.local

import androidx.room.Entity
import com.example.news.newsFeatures.domain.model.Article
import com.example.news.newsFeatures.domain.model.NewsResponse
import com.google.gson.annotations.SerializedName

@Entity(tableName = "newsTable")
data class NewsResponseEntity(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
) {
    fun toNewsResponse(): NewsResponse {
        return NewsResponse(
            articles = articles,
            status = status,
            totalResults = totalResults
        )
    }
}
