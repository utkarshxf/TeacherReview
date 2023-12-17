package com.orion.templete.Domain.Repository

import com.orion.templete.Data.Model.UserDTO

interface GetUserRepo {
    suspend fun getUser():UserDTO
}