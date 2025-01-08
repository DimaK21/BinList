package ru.kryu.binlist.presentation.cardlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.kryu.binlist.domain.CardListRepository
import javax.inject.Inject

@HiltViewModel
class CardListViewModel @Inject constructor(
    private val cardListRepository: CardListRepository
) : ViewModel() {
    private val _state = MutableStateFlow(CardListState())
    val state: StateFlow<CardListState> = _state.asStateFlow()

    init {
        getCardList()
    }

    private fun getCardList() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                _state.value.copy(isLoading = true)
            }
            val list = cardListRepository.getCardList()
            if (list.isNotEmpty()) {
                _state.update {
                    _state.value.copy(
                        cardList = list,
                        isLoading = false,
                        errorMessage = ""
                    )
                }
            } else {
                _state.update {
                    _state.value.copy(
                        cardList = emptyList(),
                        isLoading = false,
                        errorMessage = "Empty history"
                    )
                }
                delay(100L)
                _state.update {
                    _state.value.copy(errorMessage = "")
                }
            }
        }
    }
}
