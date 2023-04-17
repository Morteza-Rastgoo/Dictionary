package com.khosousi.dictionary.feature.search.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khosousi.dictionary.feature.search.domain.use_case.Search
import com.khosousi.dictionary.feature.search.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Morteza Rastgoo on 2023-04-16.
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val search: Search
) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val eventFlow = _uiEvent.asSharedFlow()

    private var searchJob: Job? = null

    fun onSearch(word: String): Unit {
        _searchQuery.value = word
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            search(word)
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                wordInfoItems = result.data ?: emptyList(),
                                loading = false
                            )
                        }

                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                wordInfoItems = result.data ?: emptyList(),
                                loading = false
                            )
                            _uiEvent.emit(UiEvent.ShowSnackBar(result.message ?: "Unknown error"))
                        }

                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                wordInfoItems = result.data ?: emptyList(),
                                loading = true
                            )
                        }
                    }
                }.launchIn(this)

        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
    }
}