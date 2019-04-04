package com.jahnold.coroutines.details.domain

import androidx.lifecycle.ViewModel
import com.jahnold.coroutines.details.domain.usecase.ItemUseCase
import com.jahnold.coroutines.details.view.data.DetailsState
import io.reactivex.Observable
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val itemUseCase: ItemUseCase
): ViewModel() {

    fun getDetailsData(uuid: String?): Observable<DetailsState> {

        return when (!uuid.isNullOrEmpty()) {
            true -> itemUseCase.getAction(uuid)
            else -> Observable.just(DetailsState.Error)
        }
    }
}