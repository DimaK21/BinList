package ru.kryu.binlist.data.converter

import ru.kryu.binlist.data.database.CardDetailsEntity
import ru.kryu.binlist.data.network.dto.CardDetailsDto
import ru.kryu.binlist.domain.model.CardDetails

fun CardDetails.toEntity() = CardDetailsEntity(
    id = 0,
    scheme = scheme,
    type = type,
    brand = brand,
    country = country,
    city = city,
    bank = bank,
    website = website,
    phone = phone
)

fun CardDetailsEntity.toDomain() = CardDetails(
    scheme = scheme,
    type = type,
    brand = brand,
    country = country,
    city = city,
    bank = bank,
    website = website,
    phone = phone
)

fun CardDetailsDto.toDomain() = CardDetails(
    scheme = scheme ?: "",
    type = type ?: "",
    brand = brand ?: "",
    country = country?.name ?: "",
    city = bank?.city ?: "",
    bank = bank?.name ?: "",
    website = if (!bank?.url.isNullOrEmpty()) {
        if (bank!!.url!!.startsWith("http")) bank.url!!
        else "http://${bank.url}"
    } else {
        ""
    },
    phone = bank?.phone ?: ""
)