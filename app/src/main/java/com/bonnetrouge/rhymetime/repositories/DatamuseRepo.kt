package com.bonnetrouge.rhymetime.repositories

import android.arch.lifecycle.LiveData
import com.bonnetrouge.rhymetime.models.WordInfo
import com.bonnetrouge.rhymetime.models.WordRhymes

interface DatamuseRepo {
    fun getWordInfoLiveData(): LiveData<WordRhymes?>

    fun updateCurrentWord(word: String)

    fun getSuggestionLiveData(): LiveData<List<WordInfo>>

    fun fetchSuggestions(s: String)
}