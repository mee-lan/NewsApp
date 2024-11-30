package com.example.mynews.domain.manager.usecases.news

import com.example.mynews.data.local.NewsDao
import com.example.mynews.domain.model.Article

class SelectArticle(private val newsDao: NewsDao) {
    suspend operator fun invoke(url: String):Article?{
        return newsDao.getArticle(url = url)
    }

}