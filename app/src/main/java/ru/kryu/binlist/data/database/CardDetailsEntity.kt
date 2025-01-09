package ru.kryu.binlist.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards_table")
data class CardDetailsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val scheme: String,
    val type: String,
    val brand: String,
    val country: String,
    val city: String,
    val bank: String,
    val website: String,
    val phone: String,
)
