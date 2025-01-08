package ru.kryu.binlist.data

import kotlinx.coroutines.flow.Flow
import ru.kryu.binlist.data.database.CardDetailsDao
import ru.kryu.binlist.data.network.RetrofitNetworkClient
import ru.kryu.binlist.domain.CardIfoRepository
import ru.kryu.binlist.domain.model.CardDetails
import ru.kryu.binlist.util.Resource
import javax.inject.Inject

class CardIfoRepositoryImpl @Inject constructor(
    private val cardDetailsDao: CardDetailsDao,
    private val retrofitNetworkClient: RetrofitNetworkClient
) : CardIfoRepository {
    override fun getCardInfo(bin: String): Flow<Resource<CardDetails>> {
        TODO("Not yet implemented")
    }
}