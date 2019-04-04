package com.jahnold.coroutines.navigation

import com.jahnold.coroutines.base.navigation.Navigator
import com.jahnold.coroutines.base.view.BaseFragment
import com.jahnold.coroutines.details.view.DetailsFragment
import com.jahnold.coroutines.list.view.ListFragment
import com.jahnold.coroutines.search.view.SearchFragment
import javax.inject.Inject

class NavigatorImpl @Inject constructor(): Navigator {

    override fun getFragmentInstance(destination: Navigator.Fragments): BaseFragment {
        return when (destination) {

            is Navigator.Fragments.List -> ListFragment.create(destination.search)
            is Navigator.Fragments.Details -> DetailsFragment.create(destination.uuid)
            Navigator.Fragments.Search -> SearchFragment()
        }
    }
}