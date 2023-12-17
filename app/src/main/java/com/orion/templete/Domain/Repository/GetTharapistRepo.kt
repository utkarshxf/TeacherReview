package com.orion.templete.Domain.Repository

import com.orion.templete.Data.Model.exploreDTO

interface GetTherapistRepo {
    suspend fun getTherapist():exploreDTO
}