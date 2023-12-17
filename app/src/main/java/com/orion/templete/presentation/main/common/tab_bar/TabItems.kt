package com.orion.templete.presentation.main.common.tab_bar

import android.content.Context
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import com.orion.templete.Data.Model.BlogDTOItem
import com.orion.templete.R
import com.orion.templete.presentation.main.screens.home.main.MainScreen
import com.orion.templete.presentation.main.screens.home.subscription.SubscriptionScreen
import com.orion.templete.presentation.main.screens.home.blogs.BlogScreen
import com.orion.templete.presentation.main.screens.thought.mints.MintsScreen

typealias ComposableFun = @Composable () -> Unit
@OptIn(ExperimentalMaterial3Api::class)
sealed class TabItem(val title: String, val screens: ComposableFun) {

    data class Main(val scrollBehavior: TopAppBarScrollBehavior,val context:Context,) : TabItem(title = context.getString(
        R.string.main
    ), screens = { MainScreen(scrollBehavior) })

    data class Subscription(val scrollBehavior: TopAppBarScrollBehavior,val context:Context,) : TabItem(title = context.getString(
        R.string.subscribe
    ), screens = { SubscriptionScreen(scrollBehavior)})


    data class Mints(val context:Context) : TabItem(title = context.getString(
        R.string.mints
    ), screens = { MintsScreen() })


    data class Blog(
        val scrollBehavior: TopAppBarScrollBehavior,
        val navigateToSelectedBlog: (BlogDTOItem) -> Unit,
        val context:Context,
    ) : TabItem(title = context.getString(R.string.explore), screens = { BlogScreen(scrollBehavior, navigateToSelectedBlog ) })
}