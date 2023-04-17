package com.khosousi.dictionary.feature.search.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.khosousi.dictionary.feature.search.data.local.entity.WordInfoEntity

/**
 * Created by Morteza Rastgoo on 2023-04-16.
 */
@Dao
interface WordInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(wordInfoEntity: WordInfoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<WordInfoEntity>)


    @Query("DELETE FROM wordinfoentity WHERE word IN(:words)")
    suspend fun delete(words: List<String>)


    @Query("SELECT * FROM wordinfoentity WHERE word LIKE '%' || :word || '%' ")
    suspend fun search(word: String): List<WordInfoEntity>
}