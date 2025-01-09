package ru.kryu.binlist.presentation.search

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.kryu.binlist.R
import ru.kryu.binlist.domain.CardInfoRepository
import ru.kryu.binlist.domain.model.CardDetails
import ru.kryu.binlist.util.Resource
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val cardInfoRepository: CardInfoRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state.asStateFlow()

    fun getCardInfo(bin: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { _state.value.copy(isLoading = true, card = CardDetails()) }
            if (bin.trim().length < MIN_DIGITS) {
                _state.update {
                    _state.value.copy(
                        isLoading = false,
                        errorMessage = context.getString(R.string.minimum_4)
                    )
                }
                delay(100L)
                _state.update { _state.value.copy(errorMessage = "") }
            } else {
                cardInfoRepository.getCardInfo(bin).collect { resource ->
                    if (resource is Resource.Success) {
                        _state.update {
                            _state.value.copy(
                                isLoading = false,
                                card = resource.data!!
                            )
                        }
                    } else {
                        _state.update {
                            _state.value.copy(
                                isLoading = false,
                                errorMessage = resource.message!!
                            )
                        }
                        delay(100L)
                        _state.update { _state.value.copy(errorMessage = "") }
                    }
                }
            }
        }
    }

    companion object {
        private const val MIN_DIGITS = 6
    }
}
