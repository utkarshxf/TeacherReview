package com.orion.templete.Data.Model

import android.os.Parcelable
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.parcel.Parcelize
import java.util.Date

@Parcelize
data class Review(
    val bookId: String,
    val date: Date = Date(),
    val reviewStar: Int,
    val teachingStyle:Int,
    val internalMarks:Int,
    val externalMark:Int,
    var reviewText: String,
    val userId: String,
    val verified: Boolean
): Parcelable