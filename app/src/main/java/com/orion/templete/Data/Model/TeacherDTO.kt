package com.orion.templete.Data.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeacherDTO(
    val _id: String,
    val about: String,
    val imageUrl: String,
    val name: String,
    var review: List<Review>,
    val subject: String,
) : Parcelable