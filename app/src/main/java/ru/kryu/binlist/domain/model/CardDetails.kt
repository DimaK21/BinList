package ru.kryu.binlist.domain.model

data class CardDetails(
    val scheme: String = "",
    val type: String = "",
    val brand: String = "",
    val country: String = "",
    val city: String = "",
    val bank: String = "",
    val website: String = "",
    val phone: String = "",
)
