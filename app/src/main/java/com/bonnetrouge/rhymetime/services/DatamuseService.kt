package com.bonnetrouge.rhymetime.services

import com.bonnetrouge.rhymetime.models.WordInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface DatamuseService {

    @GET("sug")
    fun getCompletionSuggestion(@Query(value = "s") word: String): Call<List<WordInfo>>

    @GET("words")
    fun getRhymes(@Query(value = "rel_rhy") word: String): Call<List<WordInfo>>

    @GET("words")
    fun getApproxRhymes(@Query(value = "rel_nry") word: String): Call<List<WordInfo>>

    @GET("words")
    fun getHomophones(@Query(value = "rel_hom") word: String): Call<List<WordInfo>>
}
