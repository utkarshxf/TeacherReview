package com.orion.templete.presentation.main.screens.home.teachers_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orion.newsapp.util.Resource
import com.orion.templete.Data.Model.TeacherDTO
import com.orion.templete.Domain.use_case.TeacherUseCase
import com.orion.templete.util.stateholder.TeacherListStateHolder
import com.orion.templete.util.stateholder.ReviewUpdatedValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TeachersListScreenViewModel @Inject constructor(private val teacherUseCase: TeacherUseCase):
    ViewModel() {
    val TeacherList = mutableStateOf(TeacherListStateHolder())
    val UpdatedReview = mutableStateOf(ReviewUpdatedValue())
    init {
        getAllTeacher()
    }


    fun getAllTeacher(){
        teacherUseCase().onEach {

            when(it){
                is Resource.Loading->{
                    TeacherList.value = TeacherListStateHolder(isLoading = true)
                }
                is Resource.Success->{
                    TeacherList.value = TeacherListStateHolder(data = it.data)
                }
                is Resource.Error->{
                    TeacherList.value = TeacherListStateHolder(error = it.message.toString())
                }

                else -> {}
            }

        }.launchIn(viewModelScope)
    }
    fun updateBooks(Key : String , updatedBook : TeacherDTO)
    {
        teacherUseCase(Key , updatedBook).onEach {
            when(it){
                is Resource.Loading->{
                    UpdatedReview.value = ReviewUpdatedValue(isLoading = true)
                }
                is Resource.Success->{
                    UpdatedReview.value = ReviewUpdatedValue(data = it.data)
                }
                is Resource.Error->{
                    UpdatedReview.value = ReviewUpdatedValue(error = it.message.toString())
                }

                else -> {}
            }

        }.launchIn(viewModelScope)
    }
}