@file:Suppress("MemberVisibilityCanBePrivate")

package com.jahnold.coroutines.list.network.transformer

import com.google.common.truth.Truth.assertThat
import com.jahnold.coroutines.base.data.ImageSize
import com.jahnold.coroutines.base.network.data.ApiImage
import org.junit.Test

class AlbumSearchTransformerTest {

    val transformer = com.jahnold.coroutines.list.network.transformer.AlbumSearchTransformer()

    @Test
    fun `should transfer simple data from api object`() {

        val result = transformer.transform(SEARCH_ALBUM)

        assertThat(result.uuid).isEqualTo(UUID)
        assertThat(result.name).isEqualTo(NAME)
        assertThat(result.artist).isEqualTo(ARTIST)
    }

    @Test
    fun `should convert images`() {

        val result = transformer.transform(SEARCH_ALBUM)

        assertThat(result.images).hasSize(2)
        assertThat(result.images[ImageSize.SMALL]).isEqualTo(IMAGE_URL1)
        assertThat(result.images[ImageSize.MEDIUM]).isEqualTo(IMAGE_URL2)
    }

    companion object {

        const val UUID = "uuid"
        const val NAME = "name"
        const val ARTIST = "artist"

        const val IMAGE_URL1 = "url1"
        const val IMAGE_URL2 = "url2"

        val SEARCH_ALBUM = com.jahnold.coroutines.list.network.data.ApiSearchAlbum(
            uuid = UUID,
            name = NAME,
            artist = ARTIST,
            images = listOf(
                ApiImage(url = IMAGE_URL1, size = "small"),
                ApiImage(url = IMAGE_URL2, size = "medium")
            )
        )
    }
}