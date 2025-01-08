package ru.kryu.binlist.domain

import ru.kryu.binlist.domain.model.CardDetails

interface CardListRepository {
    suspend fun getCardList(): List<CardDetails>
}