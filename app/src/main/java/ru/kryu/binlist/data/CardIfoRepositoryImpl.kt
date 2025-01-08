package ru.kryu.binlist.data

import ru.kryu.binlist.data.database.CardDetailsDao
import ru.kryu.binlist.domain.CardIfoRepository
import javax.inject.Inject

class CardIfoRepositoryImpl @Inject constructor(
    private val cardDetailsDao: CardDetailsDao
) : CardIfoRepository {
}