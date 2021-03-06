package com.jahnold.coroutines.list.network.transformer

import com.jahnold.coroutines.base.data.ImageSize
import com.jahnold.coroutines.base.network.data.ApiImage
import com.jahnold.coroutines.base.transformers.Transformer
import com.jahnold.coroutines.list.domain.data.AlbumSearch
import com.jahnold.coroutines.list.network.data.ApiSearchAlbum

class AlbumSearchTransformer: Transformer<ApiSearchAlbum, AlbumSearch> {

    override fun transform(input: ApiSearchAlbum): AlbumSearch {

        return AlbumSearch(
            uuid = input.uuid.orEmpty(),
            name = input.name.orEmpty(),
            artist = input.artist.orEmpty(),
            images = getImages(input.images)
        )
    }

    private fun getImages(apiImages: List<ApiImage>?): Map<ImageSize, String> {

        return apiImages
            ?.mapNotNull { apiImage ->
                val size = ImageSize.fromString(apiImage.size)
                val url = apiImage.url

                return@mapNotNull when (size != null && url != null) {
                    true -> Pair(size, url)
                    else -> null
                }
            }
            ?.toMap()
            .orEmpty()
    }
}