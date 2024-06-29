package com.example.thingifiererer.ui.layout

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun NavGraph(startDestination: String = "list") {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        NavHost(navController = navController, startDestination = startDestination) {
            composable("login") { LoginScreen(navController) }
            composable("register") { RegisterScreen(navController) }
            composable("list") { ListDataScreen(navController) }
            composable(
                "detail/{productId}",
                arguments = listOf(navArgument("productId") { type = NavType.LongType })
            ) { backStackEntry ->
                val productId = backStackEntry.arguments?.getLong("productId") ?: return@composable
                DetailDataScreen(navController, productId)
            }
            //composable("profile") { ProfileScreen(navController) }
        }
    }
}
