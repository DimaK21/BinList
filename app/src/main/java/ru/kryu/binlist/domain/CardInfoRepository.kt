package ru.kryu.binlist.domain

import kotlinx.coroutines.flow.Flow
import ru.kryu.binlist.domain.model.CardDetails
import ru.kryu.binlist.util.Resource

interface CardInfoRepository {
    fun getCardInfo(bin: String): Flow<Resource<CardDetails>>
}