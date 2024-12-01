package com.example.mynews.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.mynews.R
import com.example.mynews.domain.model.Article
import com.example.mynews.presentation.Dimens
import com.example.mynews.presentation.common.ArticlesList
import com.example.mynews.presentation.common.SearchBar
import com.example.mynews.presentation.navgraph.Route

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(articles: LazyPagingItems<Article>,navigateToSearch: ()->Unit,navigateToDetails:(Article)->Unit) {
    val titles by remember{
        derivedStateOf {
            if (articles.itemCount> 10 ){
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9 ))
                    .joinToString (separator = " \uD83d\uDFE5 "){ it.title}
            }
            else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = Dimens.MediumPadding1)
            .statusBarsPadding()
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo1),
            contentDescription = null,
            modifier = Modifier
                .height(50.dp)
                .width(150.dp)
                .padding(horizontal = Dimens.MediumPadding1, vertical = Dimens.smallPadding),
            contentScale = ContentScale.Crop
            )
         SearchBar(text = "",
             modifier = Modifier.padding(horizontal = Dimens.MediumPadding1),
             readOnly = true,
             onValueChange = {},
             onSearch = {},
             onClick = {
                 navigateToSearch()
             }
         )

         Spacer(modifier = Modifier.height(Dimens.smallPadding))

         Text(text = titles,
             style = MaterialTheme.typography.bodyMedium,
             color = colorResource(id = R.color.body),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.MediumPadding1)
                .basicMarquee()
         )
        ArticlesList(
            articles = articles,
            modifier = Modifier.padding(horizontal = Dimens.MediumPadding1),
            onClick = {
                navigateToDetails(it)
            }
        )
    }

}