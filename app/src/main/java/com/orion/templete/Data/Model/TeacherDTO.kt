package com.orion.templete.Data.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeacherDTO(
    val about: String,
    val author: String,
    val _id: String,
    val imageUrl: String,
    val name: String,
    val subject:String,
    val teaching:Int,
    val internal:Int,
    var review: List<Review>
) : Parcelable