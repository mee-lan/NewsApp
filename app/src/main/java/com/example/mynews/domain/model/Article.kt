package com.example.mynews.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.ParcelField
import androidx.versionedparcelable.VersionedParcelize



@Entity
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    @PrimaryKey val url: String,
    val urlToImage: String
)