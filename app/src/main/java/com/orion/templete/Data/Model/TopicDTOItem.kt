package com.orion.templete.Data.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TopicDTOItem(
    val _id: String,
    val about: String,
    val name: String
): Parcelable