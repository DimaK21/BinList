package ru.kryu.binlist.data

import ru.kryu.binlist.data.converter.toDomain
import ru.kryu.binlist.data.database.CardDetailsDao
import ru.kryu.binlist.domain.CardListRepository
import ru.kryu.binlist.domain.model.CardDetails
import javax.inject.Inject

class CardListRepositoryImpl @Inject constructor(
    private val cardDetailsDao: CardDetailsDao
) : CardListRepository {

    override suspend fun getCardList(): List<CardDetails> {
        return cardDetailsDao.getCardsDetails().map { it.toDomain() }
    }
}