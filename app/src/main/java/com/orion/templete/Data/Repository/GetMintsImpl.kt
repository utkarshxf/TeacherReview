package com.orion.templete.Data.Repository

import com.orion.templete.Data.Model.MintDTO
import com.orion.templete.Data.Network.ApiService
import com.orion.templete.Domain.Repository.GetMintRepo

import com.orion.templete.util.SafeApiRequest
import javax.inject.Inject


class GetMintsImpl @Inject constructor(private val apiService: ApiService) :
    GetMintRepo, SafeApiRequest() {
    override suspend fun getMints(): MintDTO {
        val Response = safeApiRequest {
            apiService.getAllmints()
        }
        return Response
    }
}