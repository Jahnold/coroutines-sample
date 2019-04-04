@file:Suppress("HasPlatformType", "MemberVisibilityCanBePrivate")

package com.jahnold.coroutines.list.domain

import com.google.common.truth.Truth.assertThat
import com.jahnold.coroutines.base.navigation.NavigationService
import com.jahnold.coroutines.basetest.TestDispatcherHelper
import com.jahnold.coroutines.list.domain.usecase.SearchResultsUseCase
import com.jahnold.coroutines.list.view.data.ListState
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.observers.TestObserver
import org.junit.Test
import org.mockito.Mockito.mock

class ListViewModelTest {

    val searchResultsUseCase = mock<SearchResultsUseCase> {
        onBlocking { getAction(any()) } doReturn ListState.Loading
    }
    val navigationService = mock(NavigationService::class.java)
    val dispatcherHelper = TestDispatcherHelper()

    val viewModel = ListViewModel(searchResultsUseCase, navigationService, dispatcherHelper)
    val subscriber = TestObserver.create<ListState>()

    @Test
    fun `state - should return results from itemsUseCase`() {

        viewModel.state.subscribe(subscriber)
        viewModel.loadListState("")
        val result = subscriber.values().first()

        assertThat(result).isInstanceOf(ListState.Loading::class.java)
    }

    @Test
    fun `selectItem - should call navigationService`() {

        val uuid = "uuid"
        viewModel.selectItem(uuid)

        verify(navigationService).sendNavigationEvent(any())
    }
}