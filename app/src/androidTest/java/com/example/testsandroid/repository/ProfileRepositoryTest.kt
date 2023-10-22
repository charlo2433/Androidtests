package com.example.testsandroid.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testsandroid.data.local.model.Profile
import com.example.testsandroid.data.remote.model.ProductsResponse
import com.example.testsandroid.util.Resource

class ProfileRepositoryTest: ProfileInterface {
    private val profileList = mutableListOf<Profile>()
    private val observeProfileList = MutableLiveData<List<Profile>>(profileList)
    private var shouldReturnNetworkError = false

    fun setNetworkStatus(value: Boolean){
        shouldReturnNetworkError = value
    }

    override suspend fun insertProfile(profile: Profile) {
        profileList.add(profile)
    }

    override suspend fun deleteProfile(profile: Profile) {
        profileList.remove(profile)
    }

    override fun observeProfile(): LiveData<List<Profile>> {
       return observeProfileList
    }

    override suspend fun searchApi(productName: String): Resource<ProductsResponse> {
        return if (shouldReturnNetworkError){
            Resource.error("error", null)
        }else{
            Resource.success(ProductsResponse(0, listOf(),0, 0))
        }
    }

}