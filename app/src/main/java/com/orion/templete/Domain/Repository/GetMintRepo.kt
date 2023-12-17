package com.orion.templete.Domain.Repository

import com.orion.templete.Data.Model.MintDTO
import com.orion.templete.Data.Model.exploreDTO

interface GetMintRepo {

    suspend fun getMints(): MintDTO

}