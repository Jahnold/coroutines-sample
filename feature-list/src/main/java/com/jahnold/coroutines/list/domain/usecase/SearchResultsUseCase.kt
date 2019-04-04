package com.jahnold.coroutines.list.domain.usecase

import com.jahnold.coroutines.base.network.NetworkResult
import com.jahnold.coroutines.base.transformers.Transformer
import com.jahnold.coroutines.base.viewmodel.CoUseCase1
import com.jahnold.coroutines.list.domain.data.AlbumSearch
import com.jahnold.coroutines.list.network.ListRepository
import com.jahnold.coroutines.list.view.data.ListState
import com.jahnold.coroutines.list.view.data.ListUiModel
import javax.inject.Inject

class SearchResultsUseCase @Inject constructor(
    private val listRepository: ListRepository,
    private val transformer: Transformer<AlbumSearch, ListUiModel>
): CoUseCase1<String, ListState> {

    override suspend fun getAction(input: String): ListState {

        val result = listRepository.searchAlbums(input)

        return when (result) {
            is NetworkResult.Success -> mapToContent(result)
            is NetworkResult.Error -> ListState.Error
        }
    }

    private fun mapToContent(result: NetworkResult.Success<List<AlbumSearch>>): ListState {

        return result.body
            .map { transformer.transform(it) }
            .let { ListState.Content(it) }
    }
}