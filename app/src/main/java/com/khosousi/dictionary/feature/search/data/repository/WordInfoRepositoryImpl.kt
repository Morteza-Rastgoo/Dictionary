package com.khosousi.dictionary.feature.search.data.repository

import com.khosousi.dictionary.feature.search.data.local.WordInfoDao
import com.khosousi.dictionary.feature.search.data.remote.DictionaryAPI
import com.khosousi.dictionary.feature.search.domain.model.WordInfo
import com.khosousi.dictionary.feature.search.domain.repository.WordInfoRepository
import com.khosousi.dictionary.feature.search.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by Morteza Rastgoo on 2023-04-16.
 */
class WordInfoRepositoryImpl(
    private val api: DictionaryAPI,
    private val dao: WordInfoDao
) : WordInfoRepository {
    override fun search(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        val wordInfos = dao.search(word).map { it.toWordInfo() }
        emit(Resource.Loading(wordInfos))

        try {
            val response = api.search(word)
            dao.delete(response.mapNotNull { it.word })
            dao.insert(response.map { it.toWordInfoEntity() })
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    "Ops! What went wrong?", wordInfos
                )
            )

        } catch (e: HttpException) {
            var message = "Ops! What went wrong?"
            if (e.code() == 404) {
                message = "Not found"
            }
            emit(
                Resource.Error(
                    message, wordInfos
                )
            )

        } catch (e: Throwable) {
            emit(
                Resource.Error(
                    "Ops! What went wrong?", wordInfos
                )
            )
        }
        val newWordInfos = dao.search(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))

    }

}