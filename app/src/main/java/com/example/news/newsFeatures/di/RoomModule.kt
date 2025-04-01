package com.example.news.newsFeatures.di

import android.content.Context
import androidx.room.Room
import com.example.news.newsFeatures.data.local.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun providesRoomInstance(
        @ApplicationContext context: Context
    ): NewsDatabase {
        val db = Room.databaseBuilder(
            context.applicationContext,
            NewsDatabase::class.java,
            "news_db"
        ).build()

        return db
    }

    @Provides
    @Singleton
    fun providesDao(db: NewsDatabase) = db.newsDao()
}