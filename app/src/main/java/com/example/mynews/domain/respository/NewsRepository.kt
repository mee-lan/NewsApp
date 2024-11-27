package com.example.mynews.domain.respository

import androidx.paging.PagingData
import com.example.mynews.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(sources: List<String>): Flow<PagingData<Article>>
    fun searchNews(searchQuery:String, sources: List<String>): Flow<PagingData<Article>>
}