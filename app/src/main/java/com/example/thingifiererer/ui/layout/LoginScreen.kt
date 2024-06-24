package com.example.thingifiererer.ui.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.thingifiererer.viewmodel.AuthViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(navController: NavHostController) {
    val authViewModel: AuthViewModel = koinViewModel()
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginSuccessful by remember { mutableStateOf(false) }

    if (loginSuccessful) {
        LaunchedEffect(Unit) {
            navController.navigate("list") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            authViewModel.login(username, password)
            if (authViewModel.isLoggedIn.value) {
                loginSuccessful = true
            }
        }) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = {
            navController.navigate("register")
        }) {
            Text("Don't have an account? Register")
        }
    }
}
