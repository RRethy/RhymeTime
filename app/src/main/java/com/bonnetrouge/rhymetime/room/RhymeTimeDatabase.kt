package com.bonnetrouge.rhymetime.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.bonnetrouge.rhymetime.models.WordFavorite
import com.bonnetrouge.rhymetime.models.WordRhymes
import com.bonnetrouge.rhymetime.room.daos.FavoritesDao
import com.bonnetrouge.rhymetime.room.daos.WordsInfoDao

@Database(entities = [
    WordRhymes::class,
    WordFavorite::class
], version = 2)
@TypeConverters(com.bonnetrouge.rhymetime.commons.TypeConverters::class)
abstract class RhymeTimeDatabase : RoomDatabase() {
    abstract fun wordsInfoDao(): WordsInfoDao
    abstract fun favoritesDao(): FavoritesDao
}