package com.example.mynews.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mynews.R
import com.example.mynews.domain.model.Article
import com.example.mynews.domain.model.Source
import com.example.mynews.presentation.Dimens
import com.example.mynews.presentation.Dimens.ArticleCardSize
import com.example.mynews.presentation.Dimens.ExtraSmallPadding
import com.example.mynews.presentation.Dimens.SmallIconSize
import com.example.mynews.ui.theme.MyNewsTheme

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onCLick: () -> Unit
) {
    val context = LocalContext.current
    Row(modifier = modifier.clickable { onCLick() }) {

        AsyncImage(
            modifier = Modifier
                .size(Dimens.ArticleCardSize)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = null
        )



        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = Dimens.smallPadding2)
                .height(ArticleCardSize)
        ) {

            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.text_color),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.text_color)
                )

                Spacer(modifier = Modifier.width(Dimens.ExtraSmallPadding2))

                Icon(painter = painterResource(id = R.drawable.ic_time),
                    modifier = Modifier.size(SmallIconSize)
                    , contentDescription = null,
                    tint = MaterialTheme.colorScheme.surfaceTint)

                Spacer(modifier = Modifier.width(Dimens.ExtraSmallPadding2))
                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.text_color)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardPreview(){
MyNewsTheme {
    ArticleCard(article = Article(
        author = "Hari",
        title = "A huge fire was seen at lalitpur which killed 60 people. And further investigation in going on by Nepal Police",
        content = "A huge fire was seen at lalitpur which killed 60 people. And further investigation in going on",
        description = "A huge fire was seen at lalitpur which killed 60 people. And further investigation in going on",
        publishedAt = "2:30",
        source = Source("1","Kantipur"),
        url = "",
        urlToImage = ""
    )
    ){

    }
}
}