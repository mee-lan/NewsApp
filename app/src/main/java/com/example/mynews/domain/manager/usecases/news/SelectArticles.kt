package com.example.mynews.domain.manager.usecases.news

import com.example.mynews.data.local.NewsDao
import com.example.mynews.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticles(
private val newsDao: NewsDao
) {
     operator fun invoke():Flow<List<Article>> {
        return newsDao.getArticles()
    }
}
