package ru.kryu.binlist.data.network

import android.util.Log
import retrofit2.Response
import ru.kryu.binlist.BuildConfig
import ru.kryu.binlist.data.network.dto.CardDetailsDto
import javax.inject.Inject

class RetrofitNetworkClient @Inject constructor(
    private val binApi: BinApi,
) {
    suspend fun getCardInfo(bin: String): Response<CardDetailsDto>? {
        return try {
            binApi.getCardInfo(bin)
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) {
                Log.e("RetrofitNetworkClient", e.message.toString())
                Log.e("RetrofitNetworkClient", e.stackTraceToString())
            }
            null
        }
    }
}