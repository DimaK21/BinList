package ru.kryu.binlist.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.kryu.binlist.data.CardIfoRepositoryImpl
import ru.kryu.binlist.data.CardListRepositoryImpl
import ru.kryu.binlist.domain.CardIfoRepository
import ru.kryu.binlist.domain.CardListRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideCardIfoRepository(): CardIfoRepository =
        CardIfoRepositoryImpl()

    @Provides
    @Singleton
    fun provideCardListRepository(): CardListRepository =
        CardListRepositoryImpl()
}