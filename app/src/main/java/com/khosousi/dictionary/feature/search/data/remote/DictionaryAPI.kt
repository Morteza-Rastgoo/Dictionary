package com.khosousi.dictionary.feature.search.data.remote

import com.khosousi.dictionary.feature.search.data.remote.dto.SearchResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Morteza Rastgoo on 2023-04-16.
 */
interface DictionaryAPI {


    @GET("/api/v2/entries/en/{word}")
    suspend fun search(@Path("word") word: String): SearchResponseDto

    companion object {
        val BASE_URL = "https://api.dictionaryapi.dev/api/v2/"
    }
}