package com.khosousi.dictionary.feature.search.di

import android.app.Application
import androidx.room.Room
import com.khosousi.dictionary.feature.search.data.local.WordInfoDatabase
import com.khosousi.dictionary.feature.search.data.local.type_converters.DBTypeConverters
import com.khosousi.dictionary.feature.search.data.remote.DictionaryAPI
import com.khosousi.dictionary.feature.search.data.remote.DictionaryAPI.Companion.BASE_URL
import com.khosousi.dictionary.feature.search.data.repository.WordInfoRepositoryImpl
import com.khosousi.dictionary.feature.search.domain.repository.WordInfoRepository
import com.khosousi.dictionary.feature.search.domain.use_case.Search
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Morteza Rastgoo on 2023-04-17.
 */
@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {
    @Provides
    @Singleton
    fun providesWordInfoRepository(
        api: DictionaryAPI,
        database: WordInfoDatabase
    ): WordInfoRepository =
        WordInfoRepositoryImpl(api, database.dao)

    @Provides
    @Singleton
    fun providesSearch(repository: WordInfoRepository) = Search(repository)

    @Provides
    @Singleton
    fun providesWordInfoDatabase(app: Application) =
        Room.databaseBuilder(app, WordInfoDatabase::class.java, "word_db")
//            .addTypeConverter(GsonParser(Gson()))
            .addTypeConverter(DBTypeConverters())
            .build()

    @Provides
    @Singleton
    fun providesDictionaryAPI(): DictionaryAPI =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryAPI::class.java)


}