package com.bonnetrouge.rhymetime.repositories.impls

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.bonnetrouge.rhymetime.ext.bgPool
import com.bonnetrouge.rhymetime.ext.lazyAndroid
import com.bonnetrouge.rhymetime.models.WordInfo
import com.bonnetrouge.rhymetime.models.WordRhymes
import com.bonnetrouge.rhymetime.repositories.DatamuseRepo
import com.bonnetrouge.rhymetime.room.daos.WordsInfoDao
import com.bonnetrouge.rhymetime.services.DatamuseService
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatamuseRepoImpl @Inject constructor(
        private val datamuseService: DatamuseService,
        private val wordsInfoDao: WordsInfoDao
) : DatamuseRepo {

    private var suggestionCall: Call<List<WordInfo>>? = null

    private val suggestionsLiveData by lazyAndroid { MutableLiveData<List<WordInfo>>() }
    private val wordRhymesLiveData by lazyAndroid { MediatorLiveData<WordRhymes?>() }
    private var prevWordLiveData: LiveData<WordRhymes>? = null

    override fun getWordInfoLiveData(): LiveData<WordRhymes?> {
        return wordRhymesLiveData
    }

    override fun updateCurrentWord(word: String) {
        fetchHomophones(word)
        fetchRhymes(word)
        fetchNearRhymes(word)
        prevWordLiveData?.let { wordRhymesLiveData.removeSource(it) }
        wordRhymesLiveData.value = null
        prevWordLiveData = wordsInfoDao.getWordPackedRhymes(word)
        wordRhymesLiveData.addSource(prevWordLiveData!!) {
            wordRhymesLiveData.value = it
        }
    }

    private fun fetchHomophones(word: String) {
        datamuseService.getHomophones(word)
                .enqueue(object : Callback<List<WordInfo>> {
                    override fun onResponse(call: Call<List<WordInfo>>?, response: Response<List<WordInfo>>?) {
                        if (response == null) return

                        if (response.isSuccessful) {
                            GlobalScope.launch(bgPool) {
                                val wordRhymes = wordRhymesLiveData.value ?: WordRhymes(word)
                                wordsInfoDao.addWord(wordRhymes.apply {
                                    homophones = response.body()?.map { it.word }
                                })
                            }
                        }
                    }

                    override fun onFailure(call: Call<List<WordInfo>>?, t: Throwable?) {
                    }
                })
    }

    private fun fetchRhymes(word: String) {
        datamuseService.getRhymes(word)
                .enqueue(object : Callback<List<WordInfo>> {
                    override fun onResponse(call: Call<List<WordInfo>>?, response: Response<List<WordInfo>>?) {
                        if (response == null) return

                        if (response.isSuccessful) {
                            GlobalScope.launch(bgPool) {
                                val wordRhymes = wordRhymesLiveData.value ?: WordRhymes(word)
                                wordsInfoDao.addWord(wordRhymes.apply {
                                    rhymes = response.body()?.map { it.word }
                                })
                            }
                        }
                    }

                    override fun onFailure(call: Call<List<WordInfo>>?, t: Throwable?) {
                    }
                })
    }

    private fun fetchNearRhymes(word: String) {
        datamuseService.getApproxRhymes(word)
                .enqueue(object : Callback<List<WordInfo>> {
                    override fun onResponse(call: Call<List<WordInfo>>?, response: Response<List<WordInfo>>?) {
                        if (response == null) return

                        if (response.isSuccessful) {
                            GlobalScope.launch(bgPool) {
                                val wordRhymes = wordRhymesLiveData.value ?: WordRhymes(word)
                                wordsInfoDao.addWord(wordRhymes.apply {
                                    nearRhymes = response.body()?.map { it.word }
                                })
                            }
                        }
                    }

                    override fun onFailure(call: Call<List<WordInfo>>?, t: Throwable?) {
                    }
                })
    }

    override fun getSuggestionLiveData(): LiveData<List<WordInfo>> {
        return suggestionsLiveData
    }

    override fun fetchSuggestions(s: String) {
        suggestionCall?.cancel()
        suggestionCall = datamuseService.getCompletionSuggestion(s)
        suggestionCall!!.enqueue(object : Callback<List<WordInfo>> {
            override fun onFailure(call: Call<List<WordInfo>>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<List<WordInfo>>?, response: Response<List<WordInfo>>?) {
                response?.let {
                    if (response.isSuccessful) {
                        suggestionsLiveData.value = response.body()
                    }
                }
            }
        })
    }
}