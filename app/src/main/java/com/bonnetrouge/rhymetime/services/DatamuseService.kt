package com.bonnetrouge.rhymetime.services

import com.bonnetrouge.rhymetime.models.Suggestion
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface DatamuseService {

    @GET("sug")
    fun getCompletionSuggestion(@Query(value = "s", encoded = true) word: String): Call<List<Suggestion>>
}
