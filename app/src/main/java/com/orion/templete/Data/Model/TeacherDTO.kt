package com.orion.templete.Data.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeacherDTO(
    val _id: String,
    val about: String,
    val author: String,
    val imageUrl: String,
    val `internal`: Int,
    val name: String,
    var review: List<Review>,
    val subject: String,
    val teaching: Int
) : Parcelable