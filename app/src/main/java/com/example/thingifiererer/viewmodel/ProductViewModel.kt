package com.example.thingifiererer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thingifiererer.model.Product
import com.example.thingifiererer.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    fun fetchProducts() {
        viewModelScope.launch {
            val productList = RetrofitInstance.api.getProducts()
            _products.value = productList
        }
    }

    fun searchProducts(query: String) {
        viewModelScope.launch {
            val productList = RetrofitInstance.api.getProducts()
            _products.value = productList.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.category.contains(query, ignoreCase = true)
            }
        }
    }

    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> = _product

    fun fetchProduct(id: Long) {
        viewModelScope.launch {
            val productDetail = RetrofitInstance.api.getProduct(id)
            _product.value = productDetail
        }
    }
}
