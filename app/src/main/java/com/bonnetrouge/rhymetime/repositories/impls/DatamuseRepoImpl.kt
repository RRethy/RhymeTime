package com.bonnetrouge.rhymetime.repositories.impls

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

    override fun getCompletionResults(s: String, cb: (List<Suggestion>?) -> Unit) {
        datamuseService.getCompletionSuggestion(s).enqueue(object : Callback<List<Suggestion>> {
            override fun onFailure(call: Call<List<Suggestion>>?, t: Throwable?) {
                TODO("not implemented")
            }

            override fun onResponse(call: Call<List<Suggestion>>?, response: Response<List<Suggestion>>?) {
                response?.let {
                    if (response.isSuccessful) {
                        cb(response.body())
                    }
                }
            }
        })
    }
}