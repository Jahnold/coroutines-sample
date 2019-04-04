@file:Suppress("HasPlatformType", "MemberVisibilityCanBePrivate")

package com.jahnold.coroutines.list.network

import com.google.common.truth.Truth.assertThat
import com.jahnold.coroutines.base.network.NetworkResult
import com.jahnold.coroutines.list.network.data.ApiAlbums
import com.jahnold.coroutines.list.network.data.ApiResults
import com.jahnold.coroutines.list.network.data.ApiSearchAlbum
import com.jahnold.coroutines.list.network.data.ApiSearchResults
import com.jahnold.coroutines.list.network.transformer.AlbumSearchTransformer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response

class ListRepositoryTest {

    val api = Mockito.mock(ListApi::class.java)
    val albumSearchTransformer = Mockito.spy(AlbumSearchTransformer::class.java)

    val repo = ListRepository(api, albumSearchTransformer)

    @Test
    fun `searchAlbums - should return error when the network request is not successful`() {

        val errorResponse: Response<ApiSearchResults> = Response.error(500, ResponseBody.create(MediaType.parse("json"), "{}"))
        whenever(api.searchAlbums(any())).thenReturn( GlobalScope.async { errorResponse } )

        runBlocking {
            val result = repo.searchAlbums("")
            assertThat(result).isInstanceOf(NetworkResult.Error::class.java)
        }
    }

    @Test
    fun `searchAlbums - should return success when the network request works`() {

        val response: Response<ApiSearchResults> = Response.success(API_SEARCH_RESULTS)
        whenever(api.searchAlbums(any())).thenReturn( GlobalScope.async { response })

        runBlocking {
            val result = repo.searchAlbums("")
            assertThat(result).isInstanceOf(NetworkResult.Success::class.java)
        }
    }

    @Test
    fun `searchAlbums - should return error if the result is invalid`() {

        val badData = API_SEARCH_RESULTS.copy(results = null)
        val response: Response<ApiSearchResults> = Response.success(badData)
        whenever(api.searchAlbums(any())).thenReturn( GlobalScope.async { response })

        runBlocking {
            val result = repo.searchAlbums("")
            assertThat(result).isInstanceOf(NetworkResult.Error::class.java)
        }
    }

    companion object {

        val API_SEARCH_RESULTS = ApiSearchResults(
            results = ApiResults(
                albumMatches = ApiAlbums(
                    album = listOf(
                        ApiSearchAlbum(
                            uuid = "uuid",
                            name = "name",
                            artist = "artist",
                            images = emptyList()
                        )
                    )
                )
            )
        )
    }
}