package com.bonnetrouge.rhymetime.repositories

import android.arch.lifecycle.LiveData
import com.bonnetrouge.rhymetime.models.Suggestion

interface DatamuseRepo {
    fun getSuggestionLiveData(): LiveData<List<Suggestion>>

    fun fetchSuggestions(s: String)
}