package com.example.thingifiererer.ui.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        modifier = Modifier.fillMaxSize().padding(8.dp)
    ) {
        items(products.value) { product ->
            ProductItem(product, navController)
            Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))
        }
    }
}

@Composable
fun ProductItem(product: Product, navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("detail/${product.id}")
            }
            .padding(8.dp)
    ) {
        Image(
            painter = rememberImagePainter(product.image),
            contentDescription = product.title,
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(product.title, style = MaterialTheme.typography.titleSmall, fontSize = 16.sp)
            Text("Price: $${product.price}", style = MaterialTheme.typography.bodySmall, fontSize = 14.sp)
            Text("Category: ${product.category}", style = MaterialTheme.typography.bodySmall, fontSize = 14.sp)
        }
    }
}
