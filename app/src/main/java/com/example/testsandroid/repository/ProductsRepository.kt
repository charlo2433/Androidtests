package com.example.testsandroid.repository

import com.example.testsandroid.data.remote.ApiInterface
import com.example.testsandroid.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class ProductsRepository @Inject constructor(private var api: ApiInterface): BaseRepository() {
     suspend fun getProductsList() = safeApiCall {
        api.productsList()
    }

    override suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
        // Implement the safeApiCall function as needed
        // You can use the implementation you provided in your SafeApiCall interface
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Resource.Failure(throwable.code(), throwable.response().toString(), false)
                    }
                    else -> {
                        Resource.Failure(null, throwable.localizedMessage?.toString(), true)
                    }
                }
            }
        }
    }
}
