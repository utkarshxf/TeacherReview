package com.orion.templete.presentation.main.screens.home.blogs

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orion.newsapp.util.Resource
import com.orion.templete.Data.Model.BooksDTO
import com.orion.templete.Domain.use_case.GetBlogUseCase
import com.orion.templete.util.stateholder.BlogsStateHolder
import com.orion.templete.util.stateholder.bookUpdatedValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BlogScreenViewModel @Inject constructor(private val getBlogUseCase: GetBlogUseCase):
    ViewModel() {
    val BooksList = mutableStateOf(BlogsStateHolder())
    val UpdatedBook = mutableStateOf(bookUpdatedValue())
    init {
        getBlogs()
    }


    fun getBlogs(){
        getBlogUseCase().onEach {

            when(it){
                is Resource.Loading->{
                    BooksList.value = BlogsStateHolder(isLoading = true)
                }
                is Resource.Success->{
                    BooksList.value = BlogsStateHolder(data = it.data)
                }
                is Resource.Error->{
                    BooksList.value = BlogsStateHolder(error = it.message.toString())
                }

                else -> {}
            }

        }.launchIn(viewModelScope)
    }
    fun updateBooks(Key : String , updatedBook : BooksDTO)
    {
        getBlogUseCase(Key , updatedBook).onEach {
            when(it){
                is Resource.Loading->{
                    UpdatedBook.value = bookUpdatedValue(isLoading = true)
                }
                is Resource.Success->{
                    UpdatedBook.value = bookUpdatedValue(data = it.data)
                }
                is Resource.Error->{
                    UpdatedBook.value = bookUpdatedValue(error = it.message.toString())
                }

                else -> {}
            }

        }.launchIn(viewModelScope)
    }
}