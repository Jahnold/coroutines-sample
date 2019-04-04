package com.jahnold.coroutines.base.network

import com.jahnold.coroutines.base.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class QueryParamsInterceptor @Inject constructor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val newRequest = chain.request().let { request ->

            val newUrl = request.url().newBuilder()
                .addQueryParameter("format", "json")
                .addQueryParameter("api_key", BuildConfig.LAST_FM_APIKEY)
                .build()

            request.newBuilder()
                .url(newUrl)
                .build()
        }
        return chain.proceed(newRequest)
    }
}