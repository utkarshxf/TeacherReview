package com.orion.templete.presentation.main.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.orion.templete.Data.Model.TeacherDTO
import com.orion.templete.presentation.main.common.tab_bar.TabItem
import com.orion.templete.presentation.main.common.tab_bar.Tabbar

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(scrollBehavior: TopAppBarScrollBehavior, navigateToSelectedBlog: (TeacherDTO) -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val context = LocalContext.current
        val list = listOf(
            TabItem.Profile(scrollBehavior, context),
            TabItem.TeacherListScreen(scrollBehavior, navigateToSelectedBlog, context),
            TabItem.MyReview(scrollBehavior, context)
        )
        val pagerState = rememberPagerState(initialPage = 1)
        Tabbar(pagerState, list)
    }
}

@Preview(showBackground = true)
@Composable
fun M() {
    MaterialTheme {

    }
}