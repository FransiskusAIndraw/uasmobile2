package com.example.thingifiererer.ui.layout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.koin.androidx.compose.koinViewModel
import com.example.thingifiererer.viewmodel.AuthViewModel
import java.util.regex.Pattern
import androidx.compose.material3.Text as Text1

@Composable
fun RegisterScreen(navController: NavHostController) {
    val authViewModel: AuthViewModel = koinViewModel()
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var fullName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var registrationSuccessful by rememberSaveable { mutableStateOf(false) }
    var emailError by rememberSaveable { mutableStateOf(false) }
    var phoneError by rememberSaveable { mutableStateOf(false) }

    if (registrationSuccessful) {
        LaunchedEffect(Unit) {
            navController.navigate("login") {
                popUpTo("register") { inclusive = true }
            }
        }
    }

    val emailPattern = Pattern.compile(
        "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    )

    fun validateEmail(email: String): Boolean {
        return emailPattern.matcher(email).matches()
    }

    fun validatePhoneNumber(phoneNumber: String): Boolean {
        return phoneNumber.all { it.isDigit() }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text1("Full Name") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = email,
            onValueChange = {
                email = it
                emailError = !validateEmail(it)
            },
            label = { Text1("Email") },
            isError = emailError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        if (emailError) {
            Text1("Invalid email format", color = MaterialTheme.colorScheme.error)
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = phoneNumber,
            onValueChange = {
                phoneNumber = it
                phoneError = !validatePhoneNumber(it)
            },
            label = { Text1("Phone Number") },
            isError = phoneError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            visualTransformation = VisualTransformation.None
        )
        if (phoneError) {
            Text1("Phone number can only contain digits", color = MaterialTheme.colorScheme.error)
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text1("Username") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text1("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (!emailError && !phoneError) {
                authViewModel.register(username, password, fullName, email, phoneNumber)
                if (authViewModel.isLoggedIn.value) {
                    registrationSuccessful = true
                }
            }
        }) {
            Text1("Register")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = {
            navController.navigate("login")
        }) {
            Text1("Already have an account? Login")
        }
    }
}
