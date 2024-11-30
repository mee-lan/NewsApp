package com.example.mynews.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.mynews.R
import com.example.mynews.presentation.Dimens
import com.example.mynews.presentation.common.ArticleCard
import com.example.mynews.presentation.common.ArticlesList
import com.example.mynews.presentation.navgraph.Route

@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    state: BookmarkState,
    navigate: (String)->Unit
) {

    Column(
        modifier= Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                top = Dimens.MediumPadding1,
                start = Dimens.MediumPadding1,
                end = Dimens.MediumPadding1
            )
    ) {
        Text(
            text = "Bookmarks",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.text_title)
            )
        )
        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))
        
        ArticlesList(articles = state.articles, onClick = {navigate(Route.DetailsScreen.route)})

    }
}