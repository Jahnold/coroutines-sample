package com.jahnold.coroutines.search.di

import androidx.lifecycle.ViewModel
import com.jahnold.coroutines.base.di.BaseModule
import com.jahnold.coroutines.base.di.ViewModelKey
import com.jahnold.coroutines.base.di.ViewModelModule
import com.jahnold.coroutines.search.view.SearchFragment
import com.jahnold.coroutines.search.viewmodel.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [
    ViewModelModule::class,
    BaseModule::class
])
abstract class SearchModule {

    @ContributesAndroidInjector
    abstract fun bindSearchFragment(): SearchFragment

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindListViewModel(viewModel: SearchViewModel): ViewModel
}