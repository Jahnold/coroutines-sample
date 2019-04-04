package com.jahnold.coroutines.di

import com.jahnold.coroutines.base.navigation.Navigator
import com.jahnold.coroutines.navigation.NavigatorImpl
import dagger.Module
import dagger.Provides

@Module
object MainModule {

    @Provides
    @JvmStatic
    fun providesNavigator(navigator: NavigatorImpl): Navigator = navigator
}