package ru.kryu.binlist.data.network.dto

data class CardDetailsDto(
    val number: Number?,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: Country?,
    val bank: Bank?
) {
    fun isFieldsNull(): Boolean {
        return scheme == null && type == null && brand == null && country?.name == null && bank?.name == null && bank?.url == null && bank?.city == null && bank?.phone == null
    }
}

data class Number(
    val length: Int?,
    val luhn: Boolean?
)

data class Country(
    val numeric: String?,
    val alpha2: String?,
    val name: String?,
    val emoji: String?,
    val currency: String?,
    val latitude: Int?,
    val longitude: Int?
)

data class Bank(
    val name: String?,
    val url: String?,
    val phone: String?,
    val city: String?
)