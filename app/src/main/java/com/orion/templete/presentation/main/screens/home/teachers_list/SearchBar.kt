package com.orion.templete.presentation.main.screens.home.teachers_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.orion.templete.Data.Model.TeacherDTO
import com.orion.templete.R
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBar(navigateToSelectedBlog: (TeacherDTO) -> Unit, viewModel: TeachersListScreenViewModel = hiltViewModel()) {
    var text by remember {
        mutableStateOf("")
    }
    var active by remember {
        mutableStateOf(false)
    }

    var items by remember {
        mutableStateOf(mutableStateListOf<String>())
    }
    val data = viewModel.SearchedteacherList.value

    SearchBar(
        modifier = Modifier.fillMaxWidth()
            .padding(vertical = 6.dp)
            .padding(horizontal = 12.dp),
        query = text,
        onQueryChange = {
            text = it
            items.clear()
            viewModel.getTeacherByName(name = it)
        },
        onSearch = {
            items.add(text)
            active = false
            text = ""
        },
        active = active,
        onActiveChange = {
            viewModel.getTeacherByName(name = text)
            active = it
        },
        placeholder = { Text(text = "Search") },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = stringResource(R.string.search_icon))
        },
        trailingIcon = {
            if (active) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Icon",
                    modifier = Modifier.clickable {
                        if (text.isNotEmpty()) {
                            text = ""
                        } else {
                            active = false
                        }
                    }
                )
            }
        },
        shape = RoundedCornerShape(12.dp)
    ) {
        if (text.isNotEmpty()){
            data.data?.let {
                LazyColumn() {
                    items(it) { blogCardData ->
                        BlogCard(blogCardData, navigateToSelectedBlog)
                    }
                }
            }
        }
    }
}
