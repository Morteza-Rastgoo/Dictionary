package com.khosousi.dictionary.feature.search.domain.model

data class Meaning(
    val antonyms: List<Any?>? = null,
    val definitions: List<Definition?>? = null,
    val partOfSpeech: String? = null,
    val synonyms: List<String?>? = null
)