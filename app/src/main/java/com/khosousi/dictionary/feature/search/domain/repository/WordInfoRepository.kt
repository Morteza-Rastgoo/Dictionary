package com.khosousi.dictionary.feature.search.domain.repository

import com.khosousi.dictionary.feature.search.domain.model.WordInfo
import com.khosousi.dictionary.feature.search.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Morteza Rastgoo on 2023-04-16.
 */
interface WordInfoRepository {
    fun search(word: String): Flow<Resource<List<WordInfo>>>
}