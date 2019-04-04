package com.jahnold.coroutines.list.network

import com.jahnold.coroutines.list.network.data.ApiSearchResults
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ListApi {

    @GET("?method=album.search")
    fun searchAlbums(@Query("album") search: String): Deferred<Response<ApiSearchResults>>
}