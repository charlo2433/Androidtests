package com.example.testsandroid.data.remote

import com.example.testsandroid.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

interface SafeApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Resource.Failure(throwable.code(),throwable.response().toString(),false,  )
                    }
                    else -> {
                        Resource.Failure(null, throwable.localizedMessage?.toString(), true)
                    }
                }
            }
        }
    }
}