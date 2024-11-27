package com.example.mynews.domain.manager.usecases.news

import androidx.paging.PagingData
import com.example.mynews.data.remote.NewsPagingSource
import com.example.mynews.domain.model.Article
import com.example.mynews.domain.respository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke (sources: List<String>): Flow<PagingData<Article>>{
        return newsRepository.getNews(sources=sources)
    }
}