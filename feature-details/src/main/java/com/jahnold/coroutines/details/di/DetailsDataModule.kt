package com.jahnold.coroutines.details.di

import com.jahnold.coroutines.base.transformers.Transformer
import com.jahnold.coroutines.details.domain.data.AlbumDetails
import com.jahnold.coroutines.details.network.DetailsApi
import com.jahnold.coroutines.details.network.data.ApiAlbumDetails
import com.jahnold.coroutines.details.network.transformer.AlbumDetailsTransformer
import com.jahnold.coroutines.details.view.data.DetailsUiModel
import com.jahnold.coroutines.details.view.data.DetailsUiModelTransformer
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object DetailsDataModule {

    @Provides
    @JvmStatic
    fun providesDetailsDataTransformer(): Transformer<AlbumDetails, DetailsUiModel> =
        DetailsUiModelTransformer()

    @Provides
    @JvmStatic
    fun providesAlbumDetailsTransformer(): Transformer<ApiAlbumDetails, AlbumDetails> =
        AlbumDetailsTransformer()

    @Provides
    @JvmStatic
    @Singleton
    fun providesDetailsApi(retrofit: Retrofit): DetailsApi =
        retrofit.create(DetailsApi::class.java)
}