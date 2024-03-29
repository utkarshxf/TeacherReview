package com.orion.templete.Domain.use_case

sealed class Screen(val route: String){
    object SplashScreen : Screen("splash_screen")
    object LoginScreen : Screen("login-screen")
    object MainScreen : Screen("main_screen")
    object HomeScreen : Screen("home")
    object TopicsScreen : Screen("topic_screen")
    object ThoughtsScreen : Screen("thoughtsScreen")
    object selectionCourse : Screen("select_course")
    object year : Screen("year_page")
    object Profile : Screen("profile")
    object Settings : Screen("settings")
    object SelectedBlog : Screen("selected_blog")
    object AllReview: Screen("review_all")
    object Reviews:Screen("review")
}
