package com.example.news.newsFeatures.domain.model

data class News(
    val articles: List<Article?>,
    val status: String?,
    val totalResults: Int?
)