package com.orion.templete.Data.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BooksDTO(
    val about: String,
    val author: String,
    val bookId: String,
    val imageUrl: String,
    val name: String,
    val review: List<Review>
): Parcelable