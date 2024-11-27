package com.example.mynews.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.mynews.domain.model.Article
import com.example.mynews.presentation.Dimens
import com.loc.newsapp.presentation.common.EmptyScreen

@Composable
fun ArticlesList(modifier: Modifier = Modifier,
                 articles: LazyPagingItems<Article>,
                 onClick: (Article)->Unit
) {
    val handlePagingResult = HandlePagingResult(articles)
    if (handlePagingResult){
        LazyColumn(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Dimens.smallPadding2),
            contentPadding = PaddingValues( Dimens.ExtraSmallPadding2)) {
            items(count = articles.itemCount){
                articles[it]?.let{
                    ArticleCard(article = it,
                        onCLick = { onClick(it)},
                        )
                }
            }
        }
    }
}

@Composable
fun HandlePagingResult(
    articles: LazyPagingItems<Article>
)
:Boolean {

    val loadState = articles.loadState
    val error = when{
        loadState.refresh is LoadState.Error ->loadState.refresh as LoadState.Error

        loadState.prepend is LoadState.Error ->loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error ->loadState.append as LoadState.Error
        else ->null
    }
    return when {
        loadState.refresh is LoadState.Loading->{
            ShimmerEffect()
            false
        }

        error!=null ->{
            EmptyScreen()
            false
        }

        else->true
    }
}

@Composable
private fun ShimmerEffect(modifier: Modifier = Modifier) {
    Column(verticalArrangement = Arrangement.spacedBy(Dimens.smallPadding)) {
        repeat(10){
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = Dimens.smallPadding)
            )
        }

    }
    
}
