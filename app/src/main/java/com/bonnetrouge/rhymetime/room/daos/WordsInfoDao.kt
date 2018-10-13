package com.bonnetrouge.rhymetime.room.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.bonnetrouge.rhymetime.models.WordRhymes

@Dao
interface WordsInfoDao {
    @Update
    fun updateWord(word: WordRhymes)

    @Insert(onConflict = REPLACE)
    fun addWord(word: WordRhymes)

    @Query("SELECT * FROM wordrhymes WHERE word = :word")
    fun getWordRhymesLiveData(word: String): LiveData<WordRhymes?>

    @Query("SELECT * FROM wordrhymes WHERE word = :word LIMIT 1")
    fun getWordRhymes(word: String): WordRhymes?
}
