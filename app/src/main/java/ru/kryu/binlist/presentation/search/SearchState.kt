package ru.kryu.binlist.presentation.search

import ru.kryu.binlist.domain.model.CardDetails

data class SearchState(
    val card: CardDetails = CardDetails(),
    val isLoading: Boolean = false,
    val errorMessage: String = "",
)
