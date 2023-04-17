package com.khosousi.dictionary.feature.search.data.remote.dto

import com.khosousi.dictionary.feature.search.domain.model.Definition

data class DefinitionDto(
    val antonyms: List<Any?>? = null,
    val definition: String? = null,
    val example: String? = null,
    val synonyms: List<String?>? = null
) {
    fun toDefinition(): Definition {
        return Definition(
            antonyms, definition, example, synonyms
        )
    }
}