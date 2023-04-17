package com.khosousi.dictionary.feature.search.data.remote.dto

import com.khosousi.dictionary.feature.search.domain.model.License

data class LicenseDto(
    val name: String? = null,
    val url: String? = null
) {
    fun toLicence() = License(name, url)
}