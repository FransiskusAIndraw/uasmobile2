package com.example.thingifiererer.ui.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.thingifiererer.viewmodel.AuthViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(navController: NavHostController) {
    val authViewModel: AuthViewModel = koinViewModel()
    val user by authViewModel.user.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        user?.let {
            Text(text = "Email: ${it.email}", style = MaterialTheme.typography.bodyMedium, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Username: ${it.username}", style = MaterialTheme.typography.bodyMedium, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Phone Number: ${it.phoneNumber}", style = MaterialTheme.typography.bodyMedium, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = {
                // Handle change password action
            }) {
                Text("Change Password")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                authViewModel.logout()
                navController.navigate("login") {
                    popUpTo("profile") { inclusive = true }
                }
            }) {
                Text("Log Out")
            }
        }
    }
}
