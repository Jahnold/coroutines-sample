package com.jahnold.coroutines.di

import androidx.lifecycle.ViewModel
import com.jahnold.coroutines.MainActivity
import com.jahnold.coroutines.base.di.ViewModelKey
import com.jahnold.coroutines.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}