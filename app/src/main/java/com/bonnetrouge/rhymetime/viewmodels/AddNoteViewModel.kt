package com.bonnetrouge.rhymetime.viewmodels

import android.arch.lifecycle.ViewModel
import com.bonnetrouge.rhymetime.repositories.DatamuseRepo
import javax.inject.Inject

class AddNoteViewModel @Inject constructor(
        private val datamuseRepo: DatamuseRepo
) : ViewModel() {

    fun getWordRhymes(word: String) = datamuseRepo.getWordRhymes(word)
}
