package com.orion.templete.Domain.use_case

import com.orion.newsapp.util.Resource
import com.orion.templete.Data.Model.UserDTO
import com.orion.templete.Domain.Repository.GetUserRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val getUserRepo: GetUserRepo) {

    operator fun invoke(): Flow<Resource<UserDTO>> = flow {
        emit(Resource.Loading(""))
        try {
            emit(Resource.Success(getUserRepo.getUser()))
        } catch (e: Exception) {
            emit(Resource.Error(e.message))
        }
    }

}