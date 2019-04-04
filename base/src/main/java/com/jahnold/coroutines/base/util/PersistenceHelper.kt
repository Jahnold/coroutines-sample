package com.jahnold.coroutines.base.util

interface PersistenceHelper {

    fun saveAlbumUuid(uuid: String)
    fun getAlbumUuid(): String?
}