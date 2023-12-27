package com.orion.templete.Data.Model

data class PersonDTO(
    val course: String,
    val firstName: String,
    val lastName: String,
    val personId: String,
    val type: String,
    val year: Int,
    val totalReviews:Int,
    val completedProfile:Int,
)