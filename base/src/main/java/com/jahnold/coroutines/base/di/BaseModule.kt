package com.jahnold.coroutines.base.di

import com.jahnold.coroutines.base.util.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class BaseModule {

    @Singleton
    @Binds
    abstract fun bindSchedulerHelper(schedulerHelperImpl: SchedulerHelperImpl): SchedulerHelper

    @Singleton
    @Binds
    abstract fun bindDispatcherHelper(dispatcherHelperImpl: DispatcherHelperImpl): DispatcherHelper

    @Singleton
    @Binds
    abstract fun bindPrefsHelper(persistenceHelperImpl: PersistenceHelperImpl): PersistenceHelper
}