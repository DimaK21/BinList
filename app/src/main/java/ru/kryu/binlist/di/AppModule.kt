package ru.kryu.binlist.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.kryu.binlist.data.CardIfoRepositoryImpl
import ru.kryu.binlist.data.CardListRepositoryImpl
import ru.kryu.binlist.data.database.CardDetailsDao
import ru.kryu.binlist.data.database.CardDetailsDatabase
import ru.kryu.binlist.domain.CardIfoRepository
import ru.kryu.binlist.domain.CardListRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCardIfoRepository(): CardIfoRepository =
        CardIfoRepositoryImpl()

    @Provides
    @Singleton
    fun provideCardListRepository(): CardListRepository =
        CardListRepositoryImpl()

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CardDetailsDatabase {
        return Room.databaseBuilder(context, CardDetailsDatabase::class.java, "card_db").build()
    }

    @Provides
    fun provideEventDao(database: CardDetailsDatabase): CardDetailsDao {
        return database.cardDetailsDao()
    }
}