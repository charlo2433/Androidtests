package com.example.testsandroid.data.remote.model

data class ProductsListResponse(
    val limit: Int,
    val products: List<ProductX>,
    val skip: Int,
    val total: Int
)