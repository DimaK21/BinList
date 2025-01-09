package ru.kryu.binlist.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kryu.binlist.BuildConfig
import ru.kryu.binlist.data.CardInfoRepositoryImpl
import ru.kryu.binlist.data.CardListRepositoryImpl
import ru.kryu.binlist.data.database.CardDetailsDao
import ru.kryu.binlist.data.database.CardDetailsDatabase
import ru.kryu.binlist.data.network.BinApi
import ru.kryu.binlist.data.network.RetrofitNetworkClient
import ru.kryu.binlist.domain.CardInfoRepository
import ru.kryu.binlist.domain.CardListRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCardInfoRepository(
        cardDetailsDao: CardDetailsDao,
        retrofitNetworkClient: RetrofitNetworkClient,
    ): CardInfoRepository =
        CardInfoRepositoryImpl(cardDetailsDao, retrofitNetworkClient)

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
    fun provideBinApi(okHttpClient: OkHttpClient): BinApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BinApi::class.java)

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    companion object {
        private const val BASE_URL = "https://lookup.binlist.net/"
    }
}