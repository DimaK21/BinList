package ru.kryu.binlist.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kryu.binlist.data.CardIfoRepositoryImpl
import ru.kryu.binlist.data.CardListRepositoryImpl
import ru.kryu.binlist.data.database.CardDetailsDao
import ru.kryu.binlist.data.database.CardDetailsDatabase
import ru.kryu.binlist.data.network.BinApi
import ru.kryu.binlist.data.network.RetrofitNetworkClient
import ru.kryu.binlist.domain.CardIfoRepository
import ru.kryu.binlist.domain.CardListRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCardIfoRepository(
        cardDetailsDao: CardDetailsDao,
        retrofitNetworkClient: RetrofitNetworkClient,
    ): CardIfoRepository =
        CardIfoRepositoryImpl(cardDetailsDao, retrofitNetworkClient)

    @Provides
    @Singleton
    fun provideCardListRepository(cardDetailsDao: CardDetailsDao): CardListRepository =
        CardListRepositoryImpl(cardDetailsDao)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CardDetailsDatabase {
        return Room.databaseBuilder(context, CardDetailsDatabase::class.java, "card_db").build()
    }

    @Provides
    fun provideEventDao(database: CardDetailsDatabase): CardDetailsDao {
        return database.cardDetailsDao()
    }

    @Provides
    @Singleton
    fun provideRetrofitNetworkClient(binApi: BinApi): RetrofitNetworkClient =
        RetrofitNetworkClient(binApi)

    @Provides
    @Singleton
    fun provideBinApi(): BinApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BinApi::class.java)

    companion object {
        private const val BASE_URL = "https://lookup.binlist.net/"
    }
}