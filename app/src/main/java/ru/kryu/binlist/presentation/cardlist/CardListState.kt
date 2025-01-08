package ru.kryu.binlist.presentation.cardlist

import ru.kryu.binlist.domain.model.CardDetails

data class CardListState(
    val cardList: List<CardDetails> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String = "",
)
