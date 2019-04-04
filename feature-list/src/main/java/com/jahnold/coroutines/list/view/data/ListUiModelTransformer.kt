package com.jahnold.coroutines.list.view.data

import com.jahnold.coroutines.list.domain.data.AlbumSearch
import com.jahnold.coroutines.base.data.ImageSize
import com.jahnold.coroutines.base.transformers.Transformer

class ListUiModelTransformer: Transformer<AlbumSearch, ListUiModel> {

    override fun transform(input: AlbumSearch): ListUiModel {

        return ListUiModel(
            uuid = input.uuid,
            name = input.name,
            artist = input.artist,
            imageUrl = getImageUrl(input.images)
        )
    }

    private fun getImageUrl(images: Map<ImageSize, String>): String? {

        if (images.containsKey(ImageSize.SMALL)) return images.getValue(ImageSize.SMALL)
        if (images.containsKey(ImageSize.MEDIUM)) return images.getValue(ImageSize.MEDIUM)
        if (images.containsKey(ImageSize.LARGE)) return images.getValue(ImageSize.LARGE)
        if (images.containsKey(ImageSize.EXTRA_LARGE)) return images.getValue(ImageSize.EXTRA_LARGE)

        return null
    }
}