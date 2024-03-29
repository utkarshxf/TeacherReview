package com.orion.templete.Data.Model

import android.os.Parcelable
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.parcel.Parcelize
import java.util.Date

@Parcelize
data class Review(
    val date: String,
    val externalMark: Int,
    val internalMarks: Int,
    val reviewText: String,
    val teachingStyle: Int,
    val userId: String,
    val verified: Boolean,
    val showText:Boolean,
    val anonymous:Boolean
): Parcelable