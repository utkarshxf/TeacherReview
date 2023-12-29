package com.orion.templete.presentation.main.screens.home.teachers_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orion.newsapp.util.Resource
import com.orion.templete.Data.Model.Review
import com.orion.templete.Data.Model.TeacherDTO
import com.orion.templete.Domain.use_case.TeacherUseCase
import com.orion.templete.util.stateholder.ReviewUpdatedValue
import com.orion.templete.util.stateholder.TeacherListStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TeachersListScreenViewModel @Inject constructor(private val teacherUseCase: TeacherUseCase) :
    ViewModel() {
    val TeacherList = mutableStateOf(TeacherListStateHolder())
    val SearchedteacherList = mutableStateOf(TeacherListStateHolder())
    val UpdatedReview = mutableStateOf(ReviewUpdatedValue())

    init {
        getAllTeacher()
    }

    fun getAllTeacher() {
        teacherUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    TeacherList.value = TeacherListStateHolder(isLoading = true)
                }

                is Resource.Success -> {
                    TeacherList.value = TeacherListStateHolder(data = it.data)
                }

                is Resource.Error -> {
                    TeacherList.value = TeacherListStateHolder(error = it.message.toString())
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun getTeacherByName(name: String) {

        teacherUseCase(name).onEach {

            when (it) {

                is Resource.Loading -> {
                    SearchedteacherList.value = TeacherListStateHolder(isLoading = true)
                }

                is Resource.Success -> {
                    SearchedteacherList.value = TeacherListStateHolder(data = it.data)
                }

                is Resource.Error -> {
                    SearchedteacherList.value = TeacherListStateHolder(error = it.message.toString())
                }

                else -> {}
            }

        }.launchIn(viewModelScope)
    }

    fun updateBooks(Key: String, updatedBook: TeacherDTO) {
        teacherUseCase(Key, updatedBook).onEach {
            when (it) {
                is Resource.Loading -> {
                    UpdatedReview.value = ReviewUpdatedValue(isLoading = true)
                }

                is Resource.Success -> {
                    UpdatedReview.value = ReviewUpdatedValue(data = it.data)
                }

                is Resource.Error -> {
                    UpdatedReview.value = ReviewUpdatedValue(error = it.message.toString())
                }

                else -> {}
            }

        }.launchIn(viewModelScope)
    }

    fun calculateAvarageTeaching(reviews: List<Review>): Double {
        val validReviews = reviews.filter { it.teachingStyle != 0 }
        val teachingStyle = validReviews.sumOf { it.teachingStyle }
        return if (validReviews.isNotEmpty()) teachingStyle.toDouble() / validReviews.size else 0.0
    }

    fun calculateAverageExternalMarks(reviews: List<Review>): Double {

        val validReviews = reviews.filter { it.externalMark != 0 } // Exclude reviews with external mark 0
        val totalExternalMarks = validReviews.sumOf { it.externalMark }

        return if (validReviews.isNotEmpty()) {
            totalExternalMarks.toDouble() / validReviews.size
        } else {
            0.0
        }
    }

    fun calculateAverageInternalMarks(reviews: List<Review>): Double {
        val validReviews = reviews.filter { it.internalMarks != 0 }
        val totalInternalMarks = validReviews.sumOf { it.internalMarks }
        return if (validReviews.isNotEmpty()) totalInternalMarks.toDouble() / validReviews.size else 0.0
    }
    fun calculateOverallStarRating(reviews: List<Review>): Int {
        val weightTeaching = 0.20
        val weightExternalMarks = 0.35
        val weightInternalMarks = 0.45

        val avgTeaching = calculateAvarageTeaching(reviews)
        val avgExternalMarks = calculateAverageExternalMarks(reviews)
        val avgInternalMarks = calculateAverageInternalMarks(reviews)

        val overallRating = (avgTeaching * weightTeaching +
                avgExternalMarks * weightExternalMarks +
                avgInternalMarks * weightInternalMarks) / (weightTeaching + weightExternalMarks + weightInternalMarks)

        return (overallRating).toInt()
    }

}