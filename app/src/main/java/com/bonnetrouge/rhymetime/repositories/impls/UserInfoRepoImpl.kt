package com.bonnetrouge.rhymetime.repositories.impls

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.bonnetrouge.rhymetime.ext.bgPool
import com.bonnetrouge.rhymetime.models.WordFavorite
import com.bonnetrouge.rhymetime.repositories.UserInfoRepo
import com.bonnetrouge.rhymetime.room.daos.FavoritesDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserInfoRepoImpl @Inject constructor(
        private val favoritesDao: FavoritesDao
) : UserInfoRepo {

    override fun isWordFavorite(word: String): LiveData<Boolean> {
        return Transformations.map(favoritesDao.isFavorite(word)) {
            it != 0
        }
    }

    override fun addFavorite(word: String) {
        GlobalScope.launch(bgPool) {
            favoritesDao.addFavorite(WordFavorite(word))
        }
    }

    override fun removeFavorite(word: String) {
        GlobalScope.launch(bgPool) {
            favoritesDao.removeFavorite(WordFavorite(word))
        }
    }

    override fun getAllFavorites(): LiveData<List<WordFavorite>> {
        return favoritesDao.getAllFavorites()
    }
}