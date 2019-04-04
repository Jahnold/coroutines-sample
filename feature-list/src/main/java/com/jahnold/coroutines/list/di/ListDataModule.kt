package com.jahnold.coroutines.list.di

import com.jahnold.coroutines.list.network.transformer.AlbumSearchTransformer
import com.jahnold.coroutines.base.transformers.Transformer
import com.jahnold.coroutines.list.domain.data.AlbumSearch
import com.jahnold.coroutines.list.network.ListApi
import com.jahnold.coroutines.list.network.data.ApiSearchAlbum
import com.jahnold.coroutines.list.view.data.ListUiModel
import com.jahnold.coroutines.list.view.data.ListUiModelTransformer
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object ListDataModule {

    @Provides
    @JvmStatic
    fun providesListDataTransformer(): Transformer<AlbumSearch, ListUiModel> =
        ListUiModelTransformer()

    @Provides
    @JvmStatic
    fun providesAlbumSearchTransformer(): Transformer<ApiSearchAlbum, AlbumSearch> =
        AlbumSearchTransformer()

    @Provides
    @JvmStatic
    @Singleton
    fun providesDetailsApi(retrofit: Retrofit): ListApi =
        retrofit.create(ListApi::class.java)
}