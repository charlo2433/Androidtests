package com.example.testsandroid.repository

import androidx.lifecycle.LiveData
import com.example.testsandroid.data.local.database.ProfileDao
import com.example.testsandroid.data.local.model.Profile
import com.example.testsandroid.data.remote.ApiInterface
import com.example.testsandroid.data.remote.model.ProductsResponse
import com.example.testsandroid.util.Resource
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val profileDao: ProfileDao, private val apiInterface: ApiInterface): ProfileInterface {
    override suspend fun insertProfile(profile: Profile) {
        profileDao.insertProfile(profile)
    }

    override suspend fun deleteProfile(profile: Profile) {
       profileDao.deleteProfile(profile)
    }

    override fun observeProfile(): LiveData<List<Profile>> {
        return profileDao.observeProfile()
    }

    override suspend fun searchApi(productName: String): Resource<ProductsResponse> {
        return try {
            val response = apiInterface.searchProducts(productName)
            if(response.isSuccessful){
                response.body()?.let {
                    return@let Resource.Success(it)
                }?: Resource.Failure(response.code(),"An Unknown error occurred", false)
            }else{
                Resource.Failure(response.code(),"An Unknown error occurred", false)
            }
        }catch (e: Exception){
            Resource.Failure(500,"An Unknown error occurred", false)
        }
    }
}