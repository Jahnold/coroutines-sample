package com.jahnold.coroutines.list.network

import com.jahnold.coroutines.base.network.NetworkResult
import com.jahnold.coroutines.base.transformers.Transformer
import com.jahnold.coroutines.list.domain.data.AlbumSearch
import com.jahnold.coroutines.list.network.data.ApiSearchAlbum
import com.jahnold.coroutines.list.network.data.ApiSearchResults
import javax.inject.Inject

class ListRepository @Inject constructor(
    private val api: ListApi,
    private val albumSearchTransformer: Transformer<ApiSearchAlbum, AlbumSearch>
) {

    suspend fun searchAlbums(search: String): NetworkResult<List<AlbumSearch>> {

        val response = api.searchAlbums(search).await()
        val body = response.body()

        return when (response.isSuccessful && body != null) {
            true -> mapToSearchSuccess(body)
            else -> NetworkResult.Error()
        }
    }

    private fun mapToSearchSuccess(result: ApiSearchResults): NetworkResult<List<AlbumSearch>> {

        val albums = result.results
            ?.albumMatches
            ?.album
            ?.map { apiAlbum -> apiAlbum.let { albumSearchTransformer.transform(it) } }

        return albums?.let { NetworkResult.Success(it) } ?: NetworkResult.Error()
    }
}