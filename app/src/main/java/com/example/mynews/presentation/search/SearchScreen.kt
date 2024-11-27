package com.example.mynews.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.mynews.presentation.Dimens
import com.example.mynews.presentation.common.ArticlesList
import com.example.mynews.presentation.common.SearchBar
import com.example.mynews.presentation.navgraph.Route

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    state: SearchState,
    event: (SearchEvent)->Unit,
    navigate: (String)->Unit
) {
    Column(
        modifier= Modifier
            .padding(
                top = Dimens.MediumPadding1,
                start = Dimens.MediumPadding1,
                end = Dimens.MediumPadding1
            )
            .statusBarsPadding()
    ) {
        SearchBar(text = state.searchQuery,
            readOnly = false,
            onValueChange = {
            event(SearchEvent.UpdateSearchQuery(it))
         },
            onSearch = {event(SearchEvent.SearchNews)}
        )
        
        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))

        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticlesList(
                articles = articles,
                onClick = {
                    navigate(Route.DetailsScreen.route)
                }
             )
        }
    }
}