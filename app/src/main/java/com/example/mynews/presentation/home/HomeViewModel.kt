package com.example.mynews.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.mynews.domain.manager.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.cache
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
newsUseCases: NewsUseCases
) : ViewModel(){

    val news = newsUseCases.getNews(
        sources = listOf("abc-news","google-news-in")
    ).cachedIn(viewModelScope)

}