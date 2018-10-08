package com.bonnetrouge.rhymetime.commons.views

import android.content.Context
import android.support.design.widget.TextInputEditText
import android.util.AttributeSet
import com.bonnetrouge.rhymetime.commons.DebounceTextWatcher
import com.bonnetrouge.rhymetime.ext.lazyAndroid

class DebounceEditText @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TextInputEditText(context, attrs, defStyleAttr), DebounceTextWatcher.OnDebouncedListener {

    private val debounceTextWatcher by lazyAndroid { DebounceTextWatcher(this) }
    private var listener: DebounceTextWatcher.OnDebouncedListener? = null

    init {
        addTextChangedListener(debounceTextWatcher)
    }

    fun setTextChangedListener(listener: DebounceTextWatcher.OnDebouncedListener) {
        this.listener = listener
    }

    override fun onDebounced(s: CharSequence) {
        listener?.onDebounced(s)
    }

    override fun onPreDebounce(s: CharSequence) {
        listener?.onPreDebounce(s)
    }
}