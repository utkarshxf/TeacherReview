package com.orion.templete.Data.Model

data class exploreDTOItem(
    val doctorId: String,
    val exp: String,
    val experiences: List<String>,
    val fees: Int,
    val name: String
)