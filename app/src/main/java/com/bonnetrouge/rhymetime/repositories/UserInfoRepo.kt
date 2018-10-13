package com.bonnetrouge.rhymetime.repositories

import android.arch.lifecycle.LiveData
import com.bonnetrouge.rhymetime.models.WordFavorite

interface UserInfoRepo {
    fun isWordFavorite(word: String): LiveData<Boolean>
    fun addFavorite(word: String)
    fun removeFavorite(word: String)
    fun getAllFavorites(): LiveData<List<WordFavorite>>
}