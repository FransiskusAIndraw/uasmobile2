package com.example.thingifiererer.ui.layout


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.thingifiererer.viewmodel.AuthViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun NavGraph(startDestination: String = "login") {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = koinViewModel()
    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = if (isLoggedIn) "list" else startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("login") { LoginScreen(navController) }
            composable("register") { RegisterScreen(navController) }
            composable("list") { ListDataScreen(navController) }
            composable("search") { SearchScreen(navController) }
            composable("profile") { ProfileScreen(navController) }
            composable(
                "detail/{productId}",
                arguments = listOf(navArgument("productId") { type = NavType.LongType })
            ) { backStackEntry ->
                val productId = backStackEntry.arguments?.getLong("productId") ?: return@composable
                DetailDataScreen(navController, productId)
            }
        }
    }
}
