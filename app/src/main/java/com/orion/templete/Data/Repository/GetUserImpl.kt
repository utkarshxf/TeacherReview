package com.orion.templete.Data.Repository

import android.util.Log
import com.orion.templete.Data.Model.PersonDTO
import com.orion.templete.Data.Network.ApiService
import com.orion.templete.Domain.Repository.GetUserRepo
import com.orion.templete.util.SafeApiRequest
import javax.inject.Inject

class GetUserImpl @Inject constructor(private val apiService: ApiService) :
    GetUserRepo, SafeApiRequest() {

    override suspend fun getUser(): PersonDTO {
        val Response = safeApiRequest {
            apiService.currentUser()
        }
        return Response
    }

}