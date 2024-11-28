package com.example.mynews.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mynews.domain.model.Article
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {
    @Insert
    suspend fun upsert(article: Article)

    @Delete
    suspend fun delete(article: Article)

    @Query("SELECT * FROM article")
    fun getArticles(): Flow<List<Article>>
}