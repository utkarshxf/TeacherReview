package com.orion.templete.presentation.main.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.orion.templete.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    var text by remember {
        mutableStateOf("")
    }
    var active by remember {
        mutableStateOf(false)
    }
    val items = remember {
        mutableStateListOf("")
    }

    SearchBar(modifier = Modifier.fillMaxWidth(), query = text, onQueryChange = {
        text = it
    }, onSearch = {
        items.add(text)
        active = false
        text = ""
    }, active = active, onActiveChange = {
        active = it
    }, placeholder = { Text(text = "Search") }, leadingIcon = {
        Icon(imageVector = Icons.Default.Search, contentDescription = stringResource(R.string.search_icon))
    }, trailingIcon = {
        if (active) {
            Icon(imageVector = Icons.Default.Close,
                contentDescription = "Close Icon",
                modifier = Modifier.clickable {
                    if (text.isNotEmpty()) {
                        text = ""
                    } else {
                        active = false
                    }
                })
        }
    }) {
        items.forEach {
            Row(modifier = Modifier.padding(all = 14.dp)) {
                if (it.isNotEmpty()) {
                    Icon(
                        painter = painterResource(R.drawable.rotate_ccw),
                        contentDescription = null
                    )
                    Text(text = it)
                }
            }
        }
    }

}