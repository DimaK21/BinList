package ru.kryu.binlist.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import ru.kryu.binlist.data.network.dto.CardDetailsDto

interface BinApi {
    @GET("{bin}")
    suspend fun getCardInfo(@Path("bin") bin: String): Response<CardDetailsDto>
}