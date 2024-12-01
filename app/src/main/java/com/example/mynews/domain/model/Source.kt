package com.example.mynews.domain.model

import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.parcelize.Parcelize


@Parcelize
data class Source(
    val id: String,
    val name: String
):Parcelable