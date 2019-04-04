package com.jahnold.coroutines.di

import android.app.Application
import com.jahnold.coroutines.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @Provides
    @JvmStatic
    @Singleton
    internal fun provideApplication(app: App): Application = app
}