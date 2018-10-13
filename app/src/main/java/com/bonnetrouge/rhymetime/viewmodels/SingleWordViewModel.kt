package com.bonnetrouge.rhymetime.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.bonnetrouge.rhymetime.ext.lazyAndroid
import com.bonnetrouge.rhymetime.repositories.DatamuseRepo
import com.bonnetrouge.rhymetime.repositories.UserInfoRepo
import javax.inject.Inject

class SingleWordViewModel @Inject constructor(
        private val datamuseRepo: DatamuseRepo,
        private val userInfoRepo: UserInfoRepo
) : ViewModel() {

    fun getWordRhymes(word: String) = datamuseRepo.getWordRhymes(word)

    fun isWordFavorite(word: String): LiveData<Boolean> = userInfoRepo.isWordFavorite(word)

    fun addFavorite(word: String) = userInfoRepo.addFavorite(word)

    fun removeFavorite(word: String) = userInfoRepo.removeFavorite(word)
}