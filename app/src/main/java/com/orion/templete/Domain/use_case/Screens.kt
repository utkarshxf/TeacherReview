package com.orion.templete.Domain.use_case

sealed class Screen(val route: String){
    object SplashScreen : Screen("splash_screen")
    object LoginScreen : Screen("login-screen")
    object MainScreen : Screen("main_screen")
    object HomeScreen : Screen("home")
    object ThoughtsScreen : Screen("thoughtsScreen")
    object Profile : Screen("profile")
    object Settings : Screen("settings")
    object SelectedBlog : Screen("selected_blog")
    object Blogs: Screen("blogs")
}
