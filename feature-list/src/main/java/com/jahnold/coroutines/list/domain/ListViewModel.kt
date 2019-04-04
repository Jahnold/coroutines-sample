package com.jahnold.coroutines.list.domain

import androidx.lifecycle.ViewModel
import com.jahnold.coroutines.base.navigation.NavigationService
import com.jahnold.coroutines.base.navigation.Navigator
import com.jahnold.coroutines.base.util.DispatcherHelper
import com.jahnold.coroutines.list.domain.usecase.SearchResultsUseCase
import com.jahnold.coroutines.list.view.data.ListState
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val searchResultsUseCase: SearchResultsUseCase,
    private val navigationService: NavigationService,
    private val dispatcherHelper: DispatcherHelper
): ViewModel() {

    val state = PublishSubject.create<ListState>()

    private val viewModelJob = Job()
    private val scope = CoroutineScope(viewModelJob)

    fun loadListState(search: String) {

        state.onNext(ListState.Loading)
        scope.launch(dispatcherHelper.io()) {

            val result = searchResultsUseCase.getAction(search)

            withContext(dispatcherHelper.mainThread()) {
                state.onNext(result)
            }
        }
    }

    fun selectItem(uuid: String) {
        navigationService.sendNavigationEvent(Navigator.Fragments.Details(uuid))
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}