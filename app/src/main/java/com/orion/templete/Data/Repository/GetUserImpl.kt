package com.orion.templete.Data.Repository

import com.orion.templete.Data.Model.UserDTO
import com.orion.templete.Data.Model.exploreDTO
import com.orion.templete.Data.Network.ApiService
import com.orion.templete.Domain.Repository.GetTherapistRepo
import com.orion.templete.Domain.Repository.GetUserRepo
import com.orion.templete.util.SafeApiRequest
import javax.inject.Inject

class GetUserImpl @Inject constructor(private val apiService: ApiService) :
    GetUserRepo, SafeApiRequest() {

    override suspend fun getUser(): UserDTO {
        val Response = safeApiRequest {
            apiService.currentUser()
        }
        return Response
    }

}