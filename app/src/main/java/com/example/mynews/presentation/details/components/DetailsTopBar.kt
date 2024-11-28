package com.example.mynews.presentation.details.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.mynews.R
import com.example.mynews.presentation.Dimens
import com.example.mynews.ui.theme.MyNewsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    modifier: Modifier = Modifier,
    onBrowsingClick: ()->Unit,
    onBookmarkClick: ()->Unit,
    onShareClick: ()->Unit,
    onBackClick: ()->Unit
    ) {
    TopAppBar(
        title = { Text(text = "Details", style = MaterialTheme.typography.titleMedium)},
        modifier=Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = colorResource(id = R.color.body),
            navigationIconContentColor = colorResource(id = R.color.body),
        ),
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = onBackClick) {
                Icon(painter = painterResource(id = R.drawable.ic_bookmark),
                    contentDescription = null)
            }
            IconButton(onClick = onBackClick) {
              Icon(imageVector = Icons.Default.Share, contentDescription = null)
            }
            IconButton(onClick = onBackClick) {
                Icon(painter = painterResource(id = R.drawable.ic_network),
                    contentDescription = null)
            }
        }
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewTopBar(modifier: Modifier = Modifier) {
    MyNewsTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            DetailsTopBar(
                onBrowsingClick = { /*TODO*/ },
                onBackClick = {},
                onBookmarkClick = { },
                onShareClick = {})
        }
    }
}