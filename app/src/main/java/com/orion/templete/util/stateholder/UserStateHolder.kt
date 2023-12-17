package com.orion.templete.util.stateholder

import com.orion.templete.Data.Model.UserDTO


data class UserStateHolder(
    val isLoading: Boolean = false,
    val data: UserDTO? = null,
    val error: String = ""
)
