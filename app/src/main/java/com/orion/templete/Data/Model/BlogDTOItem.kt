package com.orion.templete.Data.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BlogDTOItem(
    val blogId: String,
    val `data`: String,
    val imageUrl: String,
    val name: String,
    val title: String,
    val username: String,
    val views: Int
) : Parcelable