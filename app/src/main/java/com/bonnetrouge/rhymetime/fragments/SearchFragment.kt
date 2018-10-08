package com.bonnetrouge.rhymetime.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bonnetrouge.rhymetime.R
import com.bonnetrouge.rhymetime.commons.DebounceTextWatcher
import com.bonnetrouge.rhymetime.ext.lazyAndroid
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : DaggerFragment(), DebounceTextWatcher.OnDebouncedListener {

    private val debounceTextWatcher by lazyAndroid { DebounceTextWatcher(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchEditText.addTextChangedListener(debounceTextWatcher)
    }

    override fun onDebounced(s: CharSequence) {
        Log.d("Quman", s.toString())
    }

    companion object {
        fun getInstance(): SearchFragment {
            return SearchFragment()
        }

        val TAG = SearchFragment::class.java.name!!
    }
}
