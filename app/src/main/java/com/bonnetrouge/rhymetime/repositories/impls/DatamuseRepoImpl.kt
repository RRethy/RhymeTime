package com.bonnetrouge.rhymetime.repositories.impls

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.bonnetrouge.rhymetime.ext.lazyAndroid
import com.bonnetrouge.rhymetime.models.Suggestion
import com.bonnetrouge.rhymetime.repositories.DatamuseRepo
import com.bonnetrouge.rhymetime.services.DatamuseService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatamuseRepoImpl @Inject constructor(private val datamuseService: DatamuseService) : DatamuseRepo {

    private var suggestionCall: Call<List<Suggestion>>? = null

    private val suggestionsLiveData by lazyAndroid { MutableLiveData<List<Suggestion>>() }

    override fun getSuggestionLiveData(): LiveData<List<Suggestion>> {
        return suggestionsLiveData
    }

    override fun fetchSuggestions(s: String) {
        suggestionCall?.cancel()
        suggestionCall = datamuseService.getCompletionSuggestion(s)
        suggestionCall!!.enqueue(object : Callback<List<Suggestion>> {
            override fun onFailure(call: Call<List<Suggestion>>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<List<Suggestion>>?, response: Response<List<Suggestion>>?) {
                response?.let {
                    if (response.isSuccessful) {
                        suggestionsLiveData.value = response.body()
                    }
                }
            }
        })
    }
}