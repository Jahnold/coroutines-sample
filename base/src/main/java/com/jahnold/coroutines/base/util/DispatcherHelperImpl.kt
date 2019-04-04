package com.jahnold.coroutines.base.util

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DispatcherHelperImpl @Inject constructor(): DispatcherHelper {

    override fun io() = Dispatchers.IO

    override fun immediate() = Dispatchers.Unconfined

    override fun computational() = Dispatchers.Default

    override fun mainThread() = Dispatchers.Main
}