package ru.kryu.binlist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CardDetailsEntity::class], version = 1)
abstract class CardDetailsDatabase : RoomDatabase() {
    abstract fun cardDetailsDao(): CardDetailsDao
}