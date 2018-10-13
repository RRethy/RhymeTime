package com.bonnetrouge.rhymetime.repositories

import android.arch.lifecycle.LiveData
import com.bonnetrouge.rhymetime.models.WordInfo
import com.bonnetrouge.rhymetime.models.WordRhymes

interface DatamuseRepo {
    fun getSuggestionLiveData(): LiveData<List<WordInfo>>

    fun fetchSuggestions(s: String)

    fun getWordRhymes(word: String): LiveData<WordRhymes?>
}