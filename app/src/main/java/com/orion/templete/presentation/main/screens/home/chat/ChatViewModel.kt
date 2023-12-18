package com.orion.templete.presentation.main.screens.home.chat

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orion.newsapp.util.Resource
import com.orion.templete.Domain.use_case.GetUserUseCase
import com.orion.templete.util.stateholder.ExploreStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val getTherapistUseCase: GetUserUseCase) :
    ViewModel() {
    val therapistList = mutableStateOf(ExploreStateHolder())

    init {
        getTherapist()
    }

    fun getTherapist() {
        getTherapistUseCase().onEach {

            when (it) {
                is Resource.Loading -> {
                    therapistList.value = ExploreStateHolder(isLoading = true)
                }

                is Resource.Success -> {
                    therapistList.value = ExploreStateHolder(data = it.data)
                }

                is Resource.Error -> {
                    therapistList.value = ExploreStateHolder(error = it.message.toString())
                }
            }

        }.launchIn(viewModelScope)
    }
}