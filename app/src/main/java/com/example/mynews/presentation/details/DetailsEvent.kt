package com.example.mynews.presentation.details

import com.example.mynews.domain.model.Article
import com.example.mynews.presentation.navgraph.Route

sealed class DetailsEvent {
    data class UpsertDeleteArticle(val article:Article): DetailsEvent()

    object RemoveSideEffect: DetailsEvent()
}