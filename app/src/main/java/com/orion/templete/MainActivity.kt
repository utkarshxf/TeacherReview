package com.orion.templete

import SelectedBlogScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.orion.templete.Data.Model.BooksDTO
import com.orion.templete.Data.Model.Review
import com.orion.templete.Domain.use_case.Screen
import com.orion.templete.presentation.login.LoginScreen
import com.orion.templete.presentation.main.MainScreen
import com.orion.templete.presentation.main.screens.course_selection.YearPage
import com.orion.templete.presentation.main.screens.course_selection.selectionCourse
import com.orion.templete.presentation.main.screens.profile.ProfileScreen
import com.orion.templete.presentation.main.screens.setting.SettingScreen
import com.orion.templete.presentation.main.screens.home.blogs.selected_blog.mints.MintsScreen
import com.orion.templete.presentation.main.screens.home.topic_screen.TopicScreen
import com.orion.templete.presentation.splash.SplashScreen
import com.orion.templete.presentation.ui.theme.TempleteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            TempleteTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.LoginScreen
                            .route
                    ) {
                        composable(route = Screen.SplashScreen.route) {
                            SplashScreen(navController = navController)
                        }
                        composable(route = Screen.LoginScreen.route) {
                            LoginScreen(navController = navController)
                        }
                        composable(route = Screen.year.route) {
                            YearPage(navController = navController)
                        }
                        composable(route = Screen.selectionCourse.route) {
                            selectionCourse(navController = navController)
                        }
                        composable(route = Screen.MainScreen.route) {
                            MainScreen(navController = navController)
                        }
                        composable(route = Screen.Profile.route) {
                            ProfileScreen()
                        }
                        composable(route = Screen.TopicsScreen.route) {
                            TopicScreen(navController)
                        }
                        composable(route = Screen.Reviews.route)
                        {
                            val ReviewScreen =
                                navController.previousBackStackEntry
                                    ?.savedStateHandle
                                    ?.get<List<Review>>("review_data")
                            if (ReviewScreen !=null)
                            {
                                MintsScreen(ReviewScreen!!)
                            }
                        }
                        composable(route = Screen.Settings.route) {
                            SettingScreen()
                        }
                        composable(route = Screen.SelectedBlog.route) {
                            val addScreenData =
                                navController.previousBackStackEntry
                                    ?.savedStateHandle
                                    ?.get<BooksDTO>("data-mapped")
                            if(addScreenData != null)
                            {
                                SelectedBlogScreen(navigateToBlogs = { navController.popBackStack()},addScreenData) { reviewsData ->
                                    navController.currentBackStackEntry?.savedStateHandle?.set( key = "review_data", value = reviewsData )
                                    navController.navigate(Screen.Reviews.route)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
