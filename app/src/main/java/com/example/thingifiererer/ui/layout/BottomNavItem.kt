package com.example.thingifiererer.ui.layout

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val title: String) {
    object List : BottomNavItem("list", Icons.Default.List, "List")
    object Profile : BottomNavItem("profile", Icons.Default.Person, "Profile")
    object Login : BottomNavItem("login", Icons.Default.Lock, "Login")
    object Register : BottomNavItem("register", Icons.Default.Add, "Register")
}
