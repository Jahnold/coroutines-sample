package com.jahnold.coroutines.viewmodel

import androidx.lifecycle.ViewModel
import com.jahnold.coroutines.base.navigation.NavigationService
import com.jahnold.coroutines.base.navigation.Navigator
import com.jahnold.coroutines.base.util.SchedulerHelper
import io.reactivex.Observable
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val navigationService: NavigationService,
    private val schedulerHelper: SchedulerHelper
) : ViewModel() {

    fun getNavigationEvents(): Observable<Navigator.Fragments> {
        return navigationService
            .getNavigationBroadcast()
            .observeOn(schedulerHelper.mainThread())
    }
}