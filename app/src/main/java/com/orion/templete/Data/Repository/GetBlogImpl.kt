package com.orion.templete.Data.Repository

import com.orion.templete.Data.Model.BlogDTO
import com.orion.templete.Data.Network.ApiService
import com.orion.templete.Domain.Repository.GetBlogRepo
import com.orion.templete.util.SafeApiRequest
import javax.inject.Inject


class GetBlogImpl @Inject constructor(private val apiService: ApiService) :
        GetBlogRepo, SafeApiRequest() {
    override suspend fun getBlogs(): BlogDTO {
        val Response = safeApiRequest {
            apiService.getAllblog()
        }
        return Response
    }

}

