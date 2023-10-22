package com.example.testsandroid.util

sealed class Resource<out T>( val data: T? = null, val message: String? = null) {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
        val errorCode: Int?,
        val errorBody: String?,
        val isNetworkError: Boolean,

        ) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}