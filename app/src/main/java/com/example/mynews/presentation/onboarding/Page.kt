package com.example.mynews.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.mynews.R

data class Page(
    val title:String,
    val description: String,
    @DrawableRes val image:Int
)

val pages= listOf(
    Page(
        title = "Welcome to MyNews",
        description = "Get latest News everyday from any where and anytime",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Welcome to MyNews",
        description = "Get latest News everyday from any where and anytime",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Welcome to MyNews",
        description = "Get latest News everyday from any where and anytime",
        image = R.drawable.onboarding3
    )
)
