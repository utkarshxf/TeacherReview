package com.orion.templete.presentation.main.screens.home.blogs

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orion.newsapp.util.Resource
import com.orion.templete.Domain.use_case.GetBlogUseCase
import com.orion.templete.util.stateholder.BlogsStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BlogScreenViewModel @Inject constructor(private val getBlogUseCase: GetBlogUseCase):
    ViewModel() {
    val Blogs = mutableStateOf(BlogsStateHolder())
    init {
        getBlogs()
    }

    fun getBlogs(){
        getBlogUseCase().onEach {

            when(it){
                is Resource.Loading->{
                    Blogs.value = BlogsStateHolder(isLoading = true)
                }
                is Resource.Success->{
                    Blogs.value = BlogsStateHolder(data = it.data)
                }
                is Resource.Error->{
                    Blogs.value = BlogsStateHolder(error = it.message.toString())
                }

                else -> {}
            }

        }.launchIn(viewModelScope)
    }
}