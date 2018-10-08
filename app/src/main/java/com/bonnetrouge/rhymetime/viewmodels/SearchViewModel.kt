package com.bonnetrouge.rhymetime.viewmodels

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.bonnetrouge.rhymetime.ext.lazyAndroid
import com.bonnetrouge.rhymetime.models.Suggestion
import com.bonnetrouge.rhymetime.repositories.DatamuseRepo
import javax.inject.Inject

class SearchViewModel @Inject constructor(
        private val datamuseRepo: DatamuseRepo
) : ViewModel() {

    val suggestionsLiveData by lazyAndroid { MediatorLiveData<List<Suggestion>?>().apply {
        addSource(datamuseRepo.getSuggestionLiveData()) {
            value = it
        }
    }}

    fun onSearchTextChanged(s: String) = datamuseRepo.fetchSuggestions(s)
}