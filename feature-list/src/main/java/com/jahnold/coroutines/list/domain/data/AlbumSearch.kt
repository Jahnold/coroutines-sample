package com.jahnold.coroutines.list.domain.data

import com.jahnold.coroutines.base.data.ImageSize

data class AlbumSearch(
    val uuid: String,
    val name: String,
    val artist: String,
    val images: Map<ImageSize, String>
)