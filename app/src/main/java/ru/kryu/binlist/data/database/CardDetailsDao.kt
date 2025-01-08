package ru.kryu.binlist.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CardDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card: CardDetailsEntity)

    @Query("SELECT * FROM cards_table")
    suspend fun getCardsDetails(): List<CardDetailsEntity>
}