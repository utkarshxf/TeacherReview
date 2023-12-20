package com.orion.templete.Data.Repository

import com.orion.templete.Data.Model.BookListDTO
import com.orion.templete.Data.Model.BooksDTO
import com.orion.templete.Data.Network.ApiService
import com.orion.templete.Domain.Repository.GetBlogRepo
import com.orion.templete.util.SafeApiRequest
import retrofit2.Response
import javax.inject.Inject


class GetBlogImpl @Inject constructor(private val apiService: ApiService) :
        GetBlogRepo, SafeApiRequest() {
    override suspend fun getBooks(): BookListDTO {
        val Response = safeApiRequest {
            apiService.getAllBooks()
        }
        return Response
    }

    override suspend fun update(key: String, updatedReviewBook: BooksDTO): BooksDTO {
        val Response = safeApiRequest {
            apiService.sendReview(updatedReviewBook ,key )
        }
        return Response
    }


}

