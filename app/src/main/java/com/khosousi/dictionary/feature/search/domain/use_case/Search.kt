package com.khosousi.dictionary.feature.search.domain.use_case

import com.khosousi.dictionary.feature.search.domain.model.WordInfo
import com.khosousi.dictionary.feature.search.domain.repository.WordInfoRepository
import com.khosousi.dictionary.feature.search.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Morteza Rastgoo on 2023-04-16.
 */
class Search(private val repository: WordInfoRepository) {
    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if (word.isEmpty()) return flow {}
        return repository.search(word)
    }
}