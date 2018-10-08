package com.bonnetrouge.rhymetime.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.bonnetrouge.rhymetime.ext.lazyAndroid
import com.bonnetrouge.rhymetime.models.Suggestion
import com.bonnetrouge.rhymetime.repositories.DatamuseRepo
import javax.inject.Inject

class SearchViewModel @Inject constructor(
        private val datamuseRepo: DatamuseRepo
) : ViewModel() {

    val suggestionsLiveData by lazyAndroid { MutableLiveData<List<Suggestion>?>() }

    fun getCompletionResults(s: String) {
        datamuseRepo.getCompletionResults(s) {
            suggestionsLiveData.value = it
        }
    }
}