package com.example.news.newsFeatures.useCase

import com.example.news.core.Resource
import com.example.news.newsFeatures.domain.model.Article
import com.example.news.newsFeatures.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<Resource<List<Article>>> {
        return repository.getNews()
    }
}