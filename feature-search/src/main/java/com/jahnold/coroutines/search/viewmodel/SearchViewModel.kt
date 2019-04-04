package com.jahnold.coroutines.search.viewmodel

import androidx.lifecycle.ViewModel
import com.jahnold.coroutines.base.navigation.NavigationService
import com.jahnold.coroutines.base.navigation.Navigator
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val navigationService: NavigationService
): ViewModel() {

    fun search(album: String?) {
        album?.let {
            navigationService.sendNavigationEvent(Navigator.Fragments.List(album))
        }
    }
}