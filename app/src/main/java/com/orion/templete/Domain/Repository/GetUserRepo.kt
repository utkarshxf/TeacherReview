package com.orion.templete.Domain.Repository

import com.orion.templete.Data.Model.PersonDTO

interface GetUserRepo {
    suspend fun getUser():PersonDTO
}