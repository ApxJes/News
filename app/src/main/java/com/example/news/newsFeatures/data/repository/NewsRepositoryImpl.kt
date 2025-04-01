package com.example.news.newsFeatures.data.repository

import com.example.news.core.Constants.API_KEY
import com.example.news.core.Resource
import com.example.news.newsFeatures.data.local.NewsDao
import com.example.news.newsFeatures.data.remote.NewsApi
import com.example.news.newsFeatures.domain.model.Article
import com.example.news.newsFeatures.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi,
    private val dao: NewsDao
): NewsRepository {
    override fun getNews(): Flow<Resource<List<Article>>> = flow{

        emit(Resource.Loading())
        val localArticle = dao.getAllArticles().firstOrNull()?.map { it.toArticle() }
        emit(Resource.Success(localArticle ?: emptyList()))

        try {

            val response = api.getNews(country = "us", apiKey = API_KEY)
            val articleEntity = response.toArticleEntity()

            dao.clearArticles()
            dao.insertArticle(articleEntity)

            emit(Resource.Success(articleEntity.map { it.toArticle() }))


        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Oop! Something went wrong please try again"))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server, please chech you internet connection"))
        }
    }

    override suspend fun refreshNews(country: String, apiKey: String) {
        try {
            val response = api.getNews(country, apiKey)
            val articleEntity = response.toArticleEntity()

            dao.clearArticles()
            dao.insertArticle(articleEntity)
        } catch (e: Exception) {
            throw  e
        }
    }
}