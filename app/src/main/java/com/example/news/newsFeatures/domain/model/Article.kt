package com.example.news.newsFeatures.domain.model

import com.example.news.newsFeatures.data.remote.dto.SourceDto

data class Article (
    val author: String?,
    val description: String?,
    val publishedAt: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?,

    val id: Int?
)