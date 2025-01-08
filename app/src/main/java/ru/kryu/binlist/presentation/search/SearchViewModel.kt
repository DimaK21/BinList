package ru.kryu.binlist.presentation.search

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.kryu.binlist.domain.CardIfoRepository
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val cardIfoRepository: CardIfoRepository
) : ViewModel() {
    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state.asStateFlow()
}
