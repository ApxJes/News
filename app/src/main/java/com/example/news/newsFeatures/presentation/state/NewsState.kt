package com.example.news.newsFeatures.presentation.state

import com.example.news.newsFeatures.domain.model.Article

data class NewsState (
    val newsState: List<Article> = emptyList(),
    val isLoading: Boolean = false
)