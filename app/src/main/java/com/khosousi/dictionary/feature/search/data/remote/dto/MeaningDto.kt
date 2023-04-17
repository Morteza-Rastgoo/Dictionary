package com.khosousi.dictionary.feature.search.data.remote.dto

import com.khosousi.dictionary.feature.search.domain.model.Meaning

data class MeaningDto(
    val antonyms: List<Any?>? = null,
    val definitions: List<DefinitionDto?>? = null,
    val partOfSpeech: String? = null,
    val synonyms: List<String?>? = null
) {
    fun toMeaning(): Meaning {
        return Meaning(antonyms, definitions?.map { it?.toDefinition() }, partOfSpeech, synonyms)
    }
}