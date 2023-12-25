package com.orion.templete.Domain.use_case

import com.orion.newsapp.util.Resource
import com.orion.templete.Data.Model.TeacherListDTO
import com.orion.templete.Data.Model.TeacherDTO
import com.orion.templete.Domain.Repository.TeacherRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class TeacherUseCase @Inject constructor(private val teacherRepo: TeacherRepo) {

    operator fun invoke(): Flow<Resource<TeacherListDTO>> = flow {
        emit(Resource.Loading(""))
        try {
            emit(Resource.Success(teacherRepo.getTeacher()))
        } catch (e: Exception) {
            emit(Resource.Error(e.message))
        }
    }

    operator fun invoke(name:String): Flow<Resource<TeacherListDTO>> = flow {
        emit(Resource.Loading(""))
        try {
            emit(Resource.Success(teacherRepo.getTeacherByName(name)))
        } catch (e: Exception) {
            emit(Resource.Error(e.message))
        }
    }

    operator fun invoke(key: String, updatedReviewBook: TeacherDTO): Flow<Resource<TeacherDTO>> = flow {
        emit(Resource.Loading(""))
        try {
            emit(Resource.Success(teacherRepo.updateTeacher(key , updatedReviewBook)))
        } catch (e:Exception)
        {
            emit(Resource.Error(e.message))
        }
    }

}