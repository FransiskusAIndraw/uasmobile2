package com.example.thingifiererer.ui.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.thingifiererer.model.Product
import com.example.thingifiererer.viewmodel.ProductViewModel

@Composable
fun ListDataScreen(navController: NavHostController) {
    val viewModel: ProductViewModel = viewModel()
    val products = viewModel.products.collectAsState()

    viewModel.fetchProducts()

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        items(products.value) { product ->
            ProductItem(product, navController)
        }
    }
}

@Composable
fun ProductItem(product: Product, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("detail/${product.id}")
            }
            .padding(8.dp)
    ) {
        Text(product.title, style = MaterialTheme.typography.titleLarge)
        Text("Price: $${product.price}", style = MaterialTheme.typography.bodySmall)
        Image(
            painter = rememberImagePainter(product.image),
            contentDescription = product.title,
            modifier = Modifier.size(128.dp).padding(8.dp)
        )
    }
}
