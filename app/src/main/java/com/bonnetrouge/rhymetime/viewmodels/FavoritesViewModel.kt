package com.bonnetrouge.rhymetime.viewmodels

import android.arch.lifecycle.ViewModel
import com.bonnetrouge.rhymetime.ext.lazyAndroid
import com.bonnetrouge.rhymetime.repositories.UserInfoRepo
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
        private val userInfoRepo: UserInfoRepo
) : ViewModel() {

    val favorites by lazyAndroid { userInfoRepo.getAllFavorites() }

    fun removeFavorite(word: String) = userInfoRepo.removeFavorite(word)
}