package com.orion.templete.presentation.main.screens.thought.mints

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orion.newsapp.util.Resource
import com.orion.templete.Domain.use_case.GetMintsUseCase
import com.orion.templete.util.stateholder.MintsStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MintsScreenViewModel @Inject constructor(private val getMintsUseCase: GetMintsUseCase):
    ViewModel() {
    val Mints = mutableStateOf(MintsStateHolder())
    init {
        getTherapist()
    }

    fun getTherapist(){
        getMintsUseCase().onEach {

            when(it){
                is Resource.Loading->{
                    Mints.value = MintsStateHolder(isLoading = true)
                }
                is Resource.Success->{
                    Mints.value = MintsStateHolder(data = it.data)
                }
                is Resource.Error->{
                    Mints.value = MintsStateHolder(error = it.message.toString())
                }
            }

        }.launchIn(viewModelScope)
    }
}