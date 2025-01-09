package ru.kryu.binlist.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.kryu.binlist.data.converter.toDomain
import ru.kryu.binlist.data.converter.toEntity
import ru.kryu.binlist.data.database.CardDetailsDao
import ru.kryu.binlist.data.network.RetrofitNetworkClient
import ru.kryu.binlist.domain.CardInfoRepository
import ru.kryu.binlist.domain.model.CardDetails
import ru.kryu.binlist.util.Resource
import javax.inject.Inject

class CardInfoRepositoryImpl @Inject constructor(
    private val cardDetailsDao: CardDetailsDao,
    private val retrofitNetworkClient: RetrofitNetworkClient
) : CardInfoRepository {

    override fun getCardInfo(bin: String): Flow<Resource<CardDetails>> = flow {
        val response = retrofitNetworkClient.getCardInfo(bin)
        when {
            response == null -> {
                emit(Resource.Error(message = "Unknown error"))
            }

            !response.isSuccessful -> {
                emit(Resource.Error(message = response.message()))
            }

            response.isSuccessful && response.body() == null -> {
                emit(Resource.Error(message = "Unknown error"))
            }

            response.isSuccessful && response.body()!!.isFieldsNull() -> {
                emit(Resource.Error(message = "Not found"))
            }

            response.isSuccessful -> {
                val cardDetails = response.body()!!.toDomain()
                cardDetailsDao.insertCard(cardDetails.toEntity())
                emit(Resource.Success(data = cardDetails))
            }
        }
    }
}