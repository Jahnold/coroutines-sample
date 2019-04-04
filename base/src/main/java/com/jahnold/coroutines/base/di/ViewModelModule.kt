package com.jahnold.coroutines.base.di

import androidx.lifecycle.ViewModelProvider
import com.jahnold.coroutines.base.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ViewModelModule {

    @Singleton
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory
}