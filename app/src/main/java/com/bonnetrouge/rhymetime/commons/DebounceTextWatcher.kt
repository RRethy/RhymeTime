package com.bonnetrouge.rhymetime.commons

import android.text.Editable
import android.text.TextWatcher
import com.bonnetrouge.rhymetime.ext.bgPool
import com.bonnetrouge.rhymetime.ext.lazyAndroid
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DebounceTextWatcher(val onDebouncedListener: OnDebouncedListener) : TextWatcher {

    private var timerJob: Job? = null

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable) {
        onDebouncedListener.onPreDebounce(s)
        timerJob?.let { timerJob!!.cancel() }
        timerJob = GlobalScope.launch(bgPool) {
            delay(500L)
            onDebouncedListener.onDebounced(s)
        }
    }

    interface OnDebouncedListener {
        fun onDebounced(s: CharSequence)
        fun onPreDebounce(s: CharSequence) {
        }
    }
}
