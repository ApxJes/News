package com.example.news.newsFeatures.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.news.newsFeatures.domain.model.Article

@Entity(tableName = "news_table")
data class ArticleEntity(
    val author: String?,
    val description: String?,
    val publishedAt: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
) {
    fun toArticle(): Article {
        return Article(
            author = author,
            description = description,
            publishedAt = publishedAt,
            title = title,
            url = url,
            urlToImage = urlToImage,
            id = id
        )
    }
}