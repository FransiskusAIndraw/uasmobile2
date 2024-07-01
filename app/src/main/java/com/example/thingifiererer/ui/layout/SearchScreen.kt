package com.example.thingifiererer.ui.layout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.thingifiererer.model.Product
import com.example.thingifiererer.viewmodel.ProductViewModel
import coil.compose.rememberImagePainter

@Composable
fun SearchScreen(navController: NavHostController) {
    val viewModel: ProductViewModel = viewModel()
    var query by remember { mutableStateOf("") }
    val products = viewModel.products.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
                viewModel.searchProducts(it)
            },
            label = { Text("Search") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(products.value) { product ->
                ProductItem(product, navController)
                Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))
            }
        }
    }
}
