package com.khosousi.dictionary.feature.search.data.remote.dto

import com.khosousi.dictionary.feature.search.data.local.entity.WordInfoEntity

data class WordInfoDto(
    val license: LicenseDto? = LicenseDto(),
    val meanings: List<MeaningDto>? = null,
    val phonetic: String? = "",
    val sourceUrls: List<String>? = null,
    val word: String? = ""
) {
    fun toWordInfoEntity() = WordInfoEntity(
        meanings?.map { it.toMeaning() },
        phonetic,
        sourceUrls,
        word
    )
}