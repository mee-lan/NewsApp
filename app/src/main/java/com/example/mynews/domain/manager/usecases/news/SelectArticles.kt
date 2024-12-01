package com.example.mynews.domain.manager.usecases.news

import com.example.mynews.data.local.NewsDao
import com.example.mynews.domain.model.Article
import com.example.mynews.domain.respository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
private val newsRepository: NewsRepository
) {
     operator fun invoke():Flow<List<Article>> {
        return newsRepository.selectArticles()
    }
}
