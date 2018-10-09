package com.bonnetrouge.rhymetime.viewmodels

import android.arch.lifecycle.ViewModel
import com.bonnetrouge.rhymetime.ext.lazyAndroid
import com.bonnetrouge.rhymetime.repositories.DatamuseRepo
import javax.inject.Inject

class SingleWordViewModel @Inject constructor(
        private val datamuseRepo: DatamuseRepo
) : ViewModel() {

    val wordDataLiveData by lazyAndroid {
        datamuseRepo.getWordInfoLiveData()
    }

    fun updateCurrentWord(word: String) {
        datamuseRepo.updateCurrentWord(word)
    }
}