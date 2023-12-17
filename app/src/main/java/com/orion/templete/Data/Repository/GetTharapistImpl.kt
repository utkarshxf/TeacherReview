package com.orion.templete.Data.Repository


import com.orion.templete.Data.Model.exploreDTO
import com.orion.templete.Data.Network.ApiService
import com.orion.templete.Domain.Repository.GetTherapistRepo
import com.orion.templete.util.SafeApiRequest
import javax.inject.Inject

class GetTharapistImpl @Inject constructor(private val apiService: ApiService) :
    GetTherapistRepo, SafeApiRequest() {

    override suspend fun getTherapist(): exploreDTO {
        val Response = safeApiRequest {
            apiService.getAllTherapist()
        }
        return Response
    }

}