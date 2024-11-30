package com.example.mynews.presentation.bookmark

import androidx.compose.runtime.mutableStateOf
import com.example.mynews.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)