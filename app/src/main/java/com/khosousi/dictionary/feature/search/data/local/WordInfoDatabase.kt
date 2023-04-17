package com.khosousi.dictionary.feature.search.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.khosousi.dictionary.feature.search.data.local.entity.WordInfoEntity
import com.khosousi.dictionary.feature.search.data.local.type_converters.DBTypeConverters

/**
 * Created by Morteza Rastgoo on 2023-04-16.
 */
@Database(
    entities = [WordInfoEntity::class],
    version = 1,
    autoMigrations = []

)
@TypeConverters(DBTypeConverters::class)
abstract class WordInfoDatabase : RoomDatabase() {

    abstract val dao: WordInfoDao
}