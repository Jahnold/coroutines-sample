package com.jahnold.coroutines.list.network.data

import com.google.gson.annotations.SerializedName
import com.jahnold.coroutines.base.network.data.ApiImage

data class ApiSearchAlbum(
    val name: String?,
    val artist: String?,

    @SerializedName("image")
    val images: List<ApiImage>?,

    @SerializedName("mbid")
    val uuid: String?
)