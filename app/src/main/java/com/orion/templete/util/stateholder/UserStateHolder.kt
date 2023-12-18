package com.orion.templete.util.stateholder


data class UserStateHolder(
    val isLoading: Boolean = false,
    val data: UserDTO? = null,
    val error: String = ""
)
