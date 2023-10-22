package com.example.testsandroid.data.remote

import com.example.testsandroid.data.remote.model.ProductsListResponse
import com.example.testsandroid.data.remote.model.ProductsResponse
import com.example.testsandroid.util.Resource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("photos")
    suspend fun searchProducts(
        @Query("q") searchQuery: String
    ): Response<ProductsResponse>

    @GET("products")
    suspend fun productsList(): ProductsListResponse
}