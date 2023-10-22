package com.example.testsandroid.repository

import androidx.lifecycle.LiveData
import com.example.testsandroid.data.local.model.Profile
import com.example.testsandroid.data.remote.model.ProductsResponse
import com.example.testsandroid.util.Resource

interface ProfileInterface {
    suspend fun insertProfile(profile: Profile)

    suspend fun deleteProfile(profile: Profile)

    fun observeProfile(): LiveData<List<Profile>>

    suspend fun searchApi(productName: String): Resource<ProductsResponse>
}