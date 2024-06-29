package com.example.thingifiererer.ui.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.thingifiererer.viewmodel.ProductViewModel

@Composable
fun DetailDataScreen(navController: NavHostController, productId: Long) {
    val viewModel: ProductViewModel = viewModel()
    val product = viewModel.product.collectAsState()

    viewModel.fetchProduct(productId)

    product.value?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(it.title, style = MaterialTheme.typography.headlineMedium)
            Text("Price: $${it.price}", style = MaterialTheme.typography.bodySmall)
            Text("Category: ${it.category}", style = MaterialTheme.typography.bodySmall)
            Text(it.description, style = MaterialTheme.typography.bodySmall)
            Image(
                painter = rememberImagePainter(it.image),
                contentDescription = it.title,
                modifier = Modifier.size(256.dp).padding(8.dp)
            )
        }
    }
}
