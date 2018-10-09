package com.bonnetrouge.rhymetime.services

import com.bonnetrouge.rhymetime.models.WordInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface DatamuseService {

    @GET("sug")
    fun getCompletionSuggestion(@Query(value = "s", encoded = true) word: String): Call<List<WordInfo>>

    @GET("words")
    fun getRhymes(@Query(value = "rel_rhy", encoded = true) word: String): Call<List<WordInfo>>

    @GET("words")
    fun getApproxRhymes(@Query(value = "rel_nry", encoded = true) word: String): Call<List<WordInfo>>

    @GET("words")
    fun getHomophones(@Query(value = "rel_hom", encoded = true) word: String): Call<List<WordInfo>>
}
