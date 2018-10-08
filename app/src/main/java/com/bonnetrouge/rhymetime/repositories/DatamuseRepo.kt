package com.bonnetrouge.rhymetime.repositories

import com.bonnetrouge.rhymetime.models.Suggestion

interface DatamuseRepo {

    fun getCompletionResults(s: String, cb: (List<Suggestion>?) -> Unit)
}