package com.example.mynews.presentation.details

import com.example.mynews.presentation.navgraph.Route

sealed class DetailsEvent {
    object SaveArticle: DetailsEvent()
}