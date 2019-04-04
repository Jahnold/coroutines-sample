package com.jahnold.coroutines.details.di

import androidx.lifecycle.ViewModel
import com.jahnold.coroutines.base.di.BaseModule
import com.jahnold.coroutines.base.di.NetworkModule
import com.jahnold.coroutines.base.di.ViewModelKey
import com.jahnold.coroutines.base.di.ViewModelModule
import com.jahnold.coroutines.details.domain.DetailsViewModel
import com.jahnold.coroutines.details.view.DetailsFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [
    ViewModelModule::class,
    BaseModule::class,
    DetailsDataModule::class,
    NetworkModule::class
])
abstract class DetailsModule {

    @ContributesAndroidInjector
    abstract fun bindDetailsFragment(): DetailsFragment

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindDetailsViewModel(viewModel: DetailsViewModel): ViewModel
}