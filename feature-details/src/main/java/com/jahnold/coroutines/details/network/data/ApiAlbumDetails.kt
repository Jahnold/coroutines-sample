package com.jahnold.coroutines.details.network.data

import com.google.gson.annotations.SerializedName
import com.jahnold.coroutines.base.network.data.ApiImage

data class ApiAlbumDetails(
    val album: ApiAlbumDetailsAlbum?
)

data class ApiAlbumDetailsAlbum(
    @SerializedName("mbid")
    val uuid: String?,
    val name: String?,
    val artist: String?,
    val image: List<ApiImage>?,
    val tracks: ApiTracks?,
    val tags: ApiTags?,
    val wiki: ApiWiki?
)