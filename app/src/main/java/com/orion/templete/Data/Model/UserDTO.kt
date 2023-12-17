package com.orion.templete.Data.Model

data class UserDTO(
    val activeDays: Int,
    val dailyReport: List<Float>,
    val firstName: String,
    val lastName: String,
    val personId: String,
    val todayTask: List<String>
)