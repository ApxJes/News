package com.example.news.newsFeatures.data.remote.dto

import com.example.news.newsFeatures.data.local.ArticleEntity
import com.example.news.newsFeatures.domain.model.Article
import com.google.gson.annotations.SerializedName

data class ArticleDto(
    @SerializedName("author")
    val author: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("source")
    val source: SourceDto?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val urlToImage: String?
) {
    fun toArticleEntity(): ArticleEntity {
        return ArticleEntity(
            author = author,
            description = description,
            publishedAt = publishedAt,
            title = title,
            url = url,
            urlToImage = urlToImage,
        )
    }
}