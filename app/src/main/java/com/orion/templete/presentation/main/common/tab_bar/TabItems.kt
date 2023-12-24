package com.orion.templete.presentation.main.common.tab_bar

import android.content.Context
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import com.orion.templete.Data.Model.TeacherDTO
import com.orion.templete.R
import com.orion.templete.presentation.main.screens.home.my_review.MyReview
import com.orion.templete.presentation.main.screens.home.profile.ProfileScreen
import com.orion.templete.presentation.main.screens.home.teachers_list.TeacherListScreen

typealias ComposableFun = @Composable () -> Unit

@OptIn(ExperimentalMaterial3Api::class)
sealed class TabItem(val title: String, val screens: ComposableFun) {

    data class Profile(val scrollBehavior: TopAppBarScrollBehavior, val context: Context) :
        TabItem(title = context.getString(
            R.string.main
        ), screens = { ProfileScreen(scrollBehavior) })

    data class MyReview(val scrollBehavior: TopAppBarScrollBehavior, val context: Context) :
        TabItem(title = "My Reviews", screens = { MyReview(scrollBehavior) })

    data class TeacherListScreen(
        val scrollBehavior: TopAppBarScrollBehavior,
        val navigateToSelectedBlog: (TeacherDTO) -> Unit,
        val context: Context,
    ) : TabItem(
        title = context.getString(R.string.explore),
        screens = { TeacherListScreen(scrollBehavior, navigateToSelectedBlog) })
}