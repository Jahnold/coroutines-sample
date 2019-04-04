package com.jahnold.coroutines.list.di

import androidx.lifecycle.ViewModel
import com.jahnold.coroutines.base.di.BaseModule
import com.jahnold.coroutines.base.di.NetworkModule
import com.jahnold.coroutines.base.di.ViewModelKey
import com.jahnold.coroutines.base.di.ViewModelModule
import com.jahnold.coroutines.list.view.ListFragment
import com.jahnold.coroutines.list.domain.ListViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [
    ViewModelModule::class,
    BaseModule::class,
    ListDataModule::class,
    NetworkModule::class
])
abstract class ListModule {

    @ContributesAndroidInjector
    abstract fun bindListFragment(): ListFragment

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    abstract fun bindListViewModel(viewModel: ListViewModel): ViewModel
}