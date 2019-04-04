package com.jahnold.coroutines.base.navigation

import com.jahnold.coroutines.base.view.BaseFragment

interface Navigator {

    sealed class Fragments {
        data class List(val search: String): Fragments()
        data class Details(val uuid: String): Fragments()
        object Search: Fragments()
    }

    fun getFragmentInstance(destination: Fragments): BaseFragment
}