package com.orion.templete.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.orion.templete.Domain.use_case.Screen
import com.orion.templete.R
import com.orion.templete.presentation.main.common.navigation_drawer.AppDrawer
import com.orion.templete.presentation.main.screens.home.HomeScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
) {
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: Screen.HomeScreen.route
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)



    ModalNavigationDrawer(drawerContent = {
        AppDrawer(route = currentRoute,
            closeDrawer = { coroutineScope.launch { drawerState.close() } },
            navigateToMain = { navController.navigate(Screen.MainScreen.route) },
            navigateToProfile = { navController.navigate(Screen.Profile.route) },
            navigateToSettings = { navController.navigate(Screen.Settings.route) })
    }, drawerState = drawerState) {
        Scaffold(topBar = {
            CenterAlignedTopAppBar(title = { Text(text = stringResource(R.string.app_name)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                ),
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    IconButton(
                        onClick = {
                            coroutineScope.launch { drawerState.open() }
                        },
                        colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.onPrimaryContainer)
                    ) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = stringResource(R.string.menu))
                    }
                })
        }, content = {
            Box(
                modifier = Modifier
                    .padding(it)
            ) {
                HomeScreen(
                    scrollBehavior = scrollBehavior,
                    navigateToSelectedBlog = { data->
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            key = "data-mapped", value = data
                        )
                        navController.navigate(Screen.SelectedBlog.route)
                    })
            }
        })
    }
}