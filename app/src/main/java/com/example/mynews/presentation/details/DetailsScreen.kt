package com.example.mynews.presentation.details

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mynews.R
import com.example.mynews.domain.model.Article
import com.example.mynews.domain.model.Source
import com.example.mynews.presentation.Dimens
import com.example.mynews.presentation.Dimens.ArticleImageHeight
import com.example.mynews.presentation.details.components.DetailsTopBar
import com.example.mynews.presentation.navgraph.Route
import com.example.mynews.ui.theme.MyNewsTheme

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    article: Article,
    event: (DetailsEvent)->Unit,
    navigateUp: ()->Unit
) {
    val context = LocalContext.current
    Column(
        modifier= Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        
        DetailsTopBar(
            onBrowsingClick = {
                              Intent(Intent.ACTION_VIEW).also {
                                  it.data = Uri.parse(article.url)
                                  if(it.resolveActivity(context.packageManager)!=null){
                                      context.startActivity(it)
                                  }
                              }
            },
            onBookmarkClick = {
                              event(DetailsEvent.SaveArticle)
            },
            onShareClick = {

                Intent(Intent.ACTION_SENDTO).also {
                    it.putExtra(Intent.EXTRA_TEXT,article.url)
                    it.type= "text/plain"
                    if(it.resolveActivity(context.packageManager)!=null){
                        context.startActivity(it)
                    }
                }
            },
            onBackClick = navigateUp
            )

        LazyColumn(
            modifier=Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = Dimens.MediumPadding1,
                end = Dimens.MediumPadding1,
                top = Dimens.MediumPadding1
            )
        ) {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context).data(article.urlToImage).build(),
                    contentDescription = null,
                    modifier= Modifier
                        .fillMaxWidth()
                        .height(ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                
                Text(text = article.title,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(id = R.color.text_color)
                )
                Spacer(modifier = Modifier.height(Dimens.smallPadding))

                Text(text = article.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(id = R.color.body)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DetailsPreview() {

    MyNewsTheme {
        Box(modifier = Modifier.background(Color.Black)) {
            DetailsScreen(
                article = Article(
                    author = "Hari",
                    title = "A huge fire lalitpur killed 60 people",
                    content = "A huge fire was seen at lalitpur which killed 60 people. And further investigation in going on. A huge fire was seen at lalitpur which killed 60 people. And further investigation in going on. A huge fire was seen at lalitpur which killed 60 people. And further investigation in going on",
                    description = "A huge fire was seen at lalitpur which killed 60 people. And further investigation in going on . ",
                    publishedAt = "2:30",
                    source = Source("1", "Kantipur"),
                    url = "",
                    urlToImage = ""
                ),
                event = {}) {
            }
        }
    }
}