package com.khosousi.dictionary.feature.search.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.khosousi.dictionary.feature.search.domain.model.Meaning
import com.khosousi.dictionary.feature.search.domain.model.WordInfo

/**
 * Created by Morteza Rastgoo on 2023-04-16.
 */

@Entity
data class WordInfoEntity(
    val meanings: List<Meaning>? = null,
    val phonetic: String? = "",
    val sourceUrls: List<String>? = null,
    val word: String? = "",
    @PrimaryKey val id: Int? = null
) {
    fun toWordInfo() = WordInfo(
        meanings,
        phonetic,
        sourceUrls,
        word
    )
}