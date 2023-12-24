package com.orion.templete.presentation.main.screens.home.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orion.newsapp.util.Resource
import com.orion.templete.Domain.use_case.GetUserUseCase
import com.orion.templete.util.stateholder.UserStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileScreenModel @Inject constructor(private val getUserUseCase: GetUserUseCase):
    ViewModel() {
        val User = mutableStateOf(UserStateHolder())
    init {
        getUser()
    }

    fun getUser(){
        getUserUseCase().onEach {

            when(it){
                is Resource.Loading->{
                    User.value = UserStateHolder(isLoading = true)
                }
                is Resource.Success->{
                    User.value = UserStateHolder(data = it.data)
                }
                is Resource.Error->{
                    User.value = UserStateHolder(error = it.message.toString())
                }
            }

        }.launchIn(viewModelScope)
    }
}