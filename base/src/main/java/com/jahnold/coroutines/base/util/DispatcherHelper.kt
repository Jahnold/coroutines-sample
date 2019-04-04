package com.jahnold.coroutines.base.util

import kotlin.coroutines.CoroutineContext

interface DispatcherHelper {

    fun io(): CoroutineContext

    fun immediate(): CoroutineContext

    fun computational(): CoroutineContext

    fun mainThread(): CoroutineContext
}