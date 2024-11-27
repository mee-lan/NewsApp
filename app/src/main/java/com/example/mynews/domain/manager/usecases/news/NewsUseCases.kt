package com.example.mynews.domain.manager.usecases.news

import com.example.mynews.presentation.search.SearchEvent

data class NewsUseCases(
   val getNews: GetNews,
   val searchNews: SearchNews
)
