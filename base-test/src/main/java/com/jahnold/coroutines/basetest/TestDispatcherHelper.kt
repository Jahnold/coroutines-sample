package com.jahnold.coroutines.basetest

import com.jahnold.coroutines.base.util.DispatcherHelper
import kotlinx.coroutines.Dispatchers

class TestDispatcherHelper: DispatcherHelper {

    override fun io() = Dispatchers.Unconfined

    override fun immediate() = Dispatchers.Unconfined

    override fun computational() = Dispatchers.Unconfined

    override fun mainThread() = Dispatchers.Unconfined
}