package com.orion.templete.Data.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Review(
    val bookId: String,
    val date: String,
    val reviewStart: String,
    val reviewText: String,
    val userId: String,
    val verified: Boolean
): Parcelable