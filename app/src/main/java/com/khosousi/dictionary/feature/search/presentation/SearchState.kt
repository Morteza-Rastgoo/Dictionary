package com.khosousi.dictionary.feature.search.presentation

import com.khosousi.dictionary.feature.search.domain.model.WordInfo

data class SearchState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val loading: Boolean = false
)
