package com.example.mynews.data.respository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mynews.data.remote.NewsApi
import com.example.mynews.data.remote.NewsPagingSource
import com.example.mynews.data.remote.SearchNewsPagingSource
import com.example.mynews.domain.model.Article
import com.example.mynews.domain.respository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi
) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
            return Pager(
                config = PagingConfig(
                    pageSize = 10,
                ),
                pagingSourceFactory = {
                    SearchNewsPagingSource(
                        newsApi = newsApi,
                        searchQuery=searchQuery,
                        sources = sources.joinToString(separator = ",")
                    )
                }
            ).flow
        }
    }
