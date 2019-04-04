@file:Suppress("HasPlatformType", "MemberVisibilityCanBePrivate")

package com.jahnold.coroutines.list.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.jahnold.coroutines.base.data.ImageSize
import com.jahnold.coroutines.base.network.NetworkResult
import com.jahnold.coroutines.list.domain.data.AlbumSearch
import com.jahnold.coroutines.list.network.ListRepository
import com.jahnold.coroutines.list.view.data.ListState
import com.jahnold.coroutines.list.view.data.ListUiModelTransformer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.spy

class SearchResultsUseCaseTest {

    val listRepository = Mockito.mock(ListRepository::class.java)
    val transformer = spy(ListUiModelTransformer::class.java)

    val useCase = SearchResultsUseCase(listRepository, transformer)

    @Test
    fun `should pass search value to network repo`() {

        val search = "album"

        runBlocking {

            whenever(listRepository.searchAlbums(any())).thenReturn(NetworkResult.Success(listOf(ALBUM)))
            useCase.getAction(search)

            verify(listRepository).searchAlbums(search)
        }
    }

    @Test
    fun `should return Error if network errors`() {

        runBlocking {
            whenever(listRepository.searchAlbums(any())).thenReturn(NetworkResult.Error())
            val result = useCase.getAction("")

            assertThat(result).isInstanceOf(ListState.Error::class.java)
        }
    }

    @Test
    fun `should transform results on success`() {

        runBlocking {
            whenever(listRepository.searchAlbums(any())).thenReturn(NetworkResult.Success(listOf(ALBUM)))
            useCase.getAction("")
        }

        verify(transformer).transform(ALBUM)
    }

    @Test
    fun `should return Content on success`() {

        runBlocking {
            whenever(listRepository.searchAlbums(any())).thenReturn(NetworkResult.Success(listOf(ALBUM)))
            val result = useCase.getAction("")

            assertThat(result).isInstanceOf(ListState.Content::class.java)
        }
    }

    companion object {

        const val UUID = "uuid"
        const val NAME = "name"
        const val ARTIST = "artist"

        const val IMAGE_URL1 = "url1"
        const val IMAGE_URL2 = "url2"

        val images = mapOf(
            ImageSize.MEDIUM to IMAGE_URL1,
            ImageSize.EXTRA_LARGE to IMAGE_URL2
        )

        val ALBUM = AlbumSearch(
            uuid = UUID,
            name = NAME,
            artist = ARTIST,
            images = images
        )
    }
}