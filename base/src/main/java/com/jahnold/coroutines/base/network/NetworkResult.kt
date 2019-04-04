package com.jahnold.coroutines.base.network

import retrofit2.Response

sealed class NetworkResult<out T> {
    data class Success<T>(val body: T) : NetworkResult<T>()
    data class Error<T>(val errorResponse: Response<T>? = null) : NetworkResult<T>()
}