package com.jahnold.coroutines.di

import com.jahnold.coroutines.App
import com.jahnold.coroutines.details.di.DetailsModule
import com.jahnold.coroutines.list.di.ListModule
import com.jahnold.coroutines.search.di.SearchModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        MainModule::class,
        ActivityModule::class,
        SearchModule::class,
        ListModule::class,
        DetailsModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}