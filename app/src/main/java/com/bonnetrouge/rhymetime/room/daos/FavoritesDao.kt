package com.bonnetrouge.rhymetime.room.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.bonnetrouge.rhymetime.models.WordFavorite

@Dao
interface FavoritesDao {

    @Query("SELECT count(1) FROM wordfavorite WHERE word = :word")
    fun isFavorite(word: String): LiveData<Int>

    @Insert(onConflict = REPLACE)
    fun addFavorite(word: WordFavorite)

    @Delete
    fun removeFavorite(word: WordFavorite)

    @Query("SELECT * FROM wordfavorite")
    fun getAllFavorites(): LiveData<List<WordFavorite>>
}