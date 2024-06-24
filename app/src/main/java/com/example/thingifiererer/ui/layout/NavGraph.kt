package com.example.thingifiererer.ui.layout

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun NavGraph(startDestination: String = "login") {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("list") { ListDataScreen(navController) }
        composable(
            "detail/{storyId}",
            arguments = listOf(navArgument("storyId") { type = NavType.LongType })
        ) { backStackEntry ->
            val storyId = backStackEntry.arguments?.getLong("storyId") ?: return@composable
            DetailDataScreen(navController, storyId)
        }
       // composable("profile") { ProfileScreen(navController) }
    }
}
