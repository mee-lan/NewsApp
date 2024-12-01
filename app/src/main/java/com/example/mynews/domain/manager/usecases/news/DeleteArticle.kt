package com.example.mynews.domain.manager.usecases.news

import com.example.mynews.data.local.NewsDao
import com.example.mynews.domain.model.Article
import com.example.mynews.domain.respository.NewsRepository

class DeleteArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article){
        newsRepository.deleteArticle(article = article)
    }
}
