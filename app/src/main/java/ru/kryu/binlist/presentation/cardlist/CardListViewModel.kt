package ru.kryu.binlist.presentation.cardlist

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.kryu.binlist.domain.CardListRepository
import javax.inject.Inject

@HiltViewModel
class CardListViewModel @Inject constructor(
    private val cardListRepository: CardListRepository
) : ViewModel() {
    private val _state = MutableStateFlow(CardListState())
    val state: StateFlow<CardListState> = _state.asStateFlow()
}
