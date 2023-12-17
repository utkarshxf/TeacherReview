package com.orion.templete.Domain.use_case

import com.orion.newsapp.util.Resource
import com.orion.templete.Data.Model.BlogDTO
import com.orion.templete.Data.Model.UserDTO
import com.orion.templete.Domain.Repository.GetBlogRepo
import com.orion.templete.Domain.Repository.GetUserRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetBlogUseCase @Inject constructor(private val getBlogRepo: GetBlogRepo) {

    operator fun invoke(): Flow<Resource<BlogDTO>> = flow {
        emit(Resource.Loading(""))
        try {
            emit(Resource.Success(getBlogRepo.getBlogs()))
        } catch (e: Exception) {
            emit(Resource.Error(e.message))
        }
    }

}