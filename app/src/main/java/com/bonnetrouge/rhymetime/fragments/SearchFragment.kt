package com.bonnetrouge.rhymetime.fragments

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.bonnetrouge.rhymetime.R
import com.bonnetrouge.rhymetime.adapters.SearchAdapter
import com.bonnetrouge.rhymetime.commons.DebounceTextWatcher
import com.bonnetrouge.rhymetime.commons.ViewModelFactory
import com.bonnetrouge.rhymetime.ext.lazyAndroid
import com.bonnetrouge.rhymetime.ext.observe
import com.bonnetrouge.rhymetime.ext.swapFragment
import com.bonnetrouge.rhymetime.listeners.RVClickListener
import com.bonnetrouge.rhymetime.models.WordInfo
import com.bonnetrouge.rhymetime.viewmodels.SearchViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject
import android.support.v4.content.ContextCompat.getSystemService
import android.view.inputmethod.InputMethodManager
import com.bonnetrouge.rhymetime.activities.SingleWordActivity


class SearchFragment : DaggerFragment(), DebounceTextWatcher.OnDebouncedListener, RVClickListener<WordInfo> {

    @Inject lateinit var viewModelFactory: ViewModelFactory

    private val searchViewModel by lazyAndroid {
        ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)
    }

    private val debounceTextWatcher by lazyAndroid { DebounceTextWatcher(this) }
    private val suggestionsAdapter by lazyAndroid { SearchAdapter(this) }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        searchViewModel.suggestionsLiveData.observe(this) {
            if (searchEditText.text.toString().isEmpty()) {
                suggestionsAdapter.submitList(null)
            } else {
                suggestionsAdapter.submitList(it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchEditText.addTextChangedListener(debounceTextWatcher)
        with (LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)) {
            recyclerView.layoutManager = this
            recyclerView.addItemDecoration(DividerItemDecoration(context, this.orientation))
        }
        recyclerView.adapter = suggestionsAdapter
        searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                openFragmentForWord(searchEditText.text.toString())
            }
            false
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onDebounced(s: CharSequence) {
        searchViewModel.onSearchTextChanged(s.toString())
    }

    override fun onPreDebounce(s: CharSequence) {
        if (s.isEmpty()) suggestionsAdapter.submitList(null)
    }

    override fun onItemClick(data: WordInfo, index: Int) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(searchEditText.windowToken, 0)
        openFragmentForWord(data.word)
    }

    private fun openFragmentForWord(word: String) {
        (activity as AppCompatActivity).swapFragment(
                R.id.fragmentContainer,
                SingleWordFragment.TAG,
                SingleWordFragment.getInstance(word),
                true
        )
    }

    companion object {
        fun getInstance(): SearchFragment {
            return SearchFragment()
        }

        val TAG = SearchFragment::class.java.name!!
    }
}
