package com.khosousi.dictionary.feature.search.data.local.type_converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.khosousi.dictionary.feature.search.domain.model.Meaning

@ProvidedTypeConverter
class DBTypeConverters {

    private val jsonParser: JsonParser = GsonParser(Gson())

//    @TypeConverter
//    fun fromLocalDateTimeString(value: String?): LocalDateTime? {
//        return value?.let {
//            LocalDateTime.parse(it)
//        }
//    }
//
//    @TypeConverter
//    fun localDateTimeToString(date: LocalDateTime?): String? {
//        return date?.toString()
//    }

    @TypeConverter
    fun fromMeaningsJson(json: String): List<Meaning> {
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json,
            object : TypeToken<ArrayList<Meaning>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toMeaningsJson(meanings: List<Meaning>): String {
        return jsonParser.toJson(
            meanings,
            object : TypeToken<ArrayList<Meaning>>() {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromStringsJson(json: String): List<String> {
        return jsonParser.fromJson<ArrayList<String>>(
            json,
            object : TypeToken<ArrayList<String>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toStringsJson(Strings: List<String>): String {
        return jsonParser.toJson(
            Strings,
            object : TypeToken<ArrayList<String>>() {}.type
        ) ?: "[]"
    }

}