package com.khosousi.dictionary.feature.search.domain.model

data class WordInfo(
    val meanings: List<Meaning>? = null,
    val phonetic: String? = "",
    val sourceUrls: List<String>? = null,
    val word: String? = ""
)