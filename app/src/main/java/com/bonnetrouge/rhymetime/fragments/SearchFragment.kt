package com.bonnetrouge.rhymetime.fragments

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bonnetrouge.rhymetime.R
import com.bonnetrouge.rhymetime.adapters.SearchAdapter
import com.bonnetrouge.rhymetime.commons.DebounceTextWatcher
import com.bonnetrouge.rhymetime.commons.ViewModelFactory
import com.bonnetrouge.rhymetime.ext.lazyAndroid
import com.bonnetrouge.rhymetime.viewmodels.SearchViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : DaggerFragment(), DebounceTextWatcher.OnDebouncedListener {

    @Inject lateinit var viewModelFactory: ViewModelFactory

    private val searchViewModel: SearchViewModel by lazyAndroid {
        ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)
    }

    private val debounceTextWatcher by lazyAndroid { DebounceTextWatcher(this) }
    private val suggestionsAdapter by lazyAndroid { SearchAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchEditText.addTextChangedListener(debounceTextWatcher)
        with (LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)) {
            recyclerView.layoutManager = this
            recyclerView.addItemDecoration(DividerItemDecoration(context, this.orientation))
        }
        recyclerView.adapter = suggestionsAdapter
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
