package com.bonnetrouge.rhymetime.fragments

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.transition.ChangeBounds
import android.support.transition.Slide
import android.support.transition.TransitionManager
import android.support.transition.TransitionSet
import android.support.transition.TransitionSet.ORDERING_SEQUENTIAL
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bonnetrouge.rhymetime.R
import com.bonnetrouge.rhymetime.adapters.SimpleWordAdapter
import com.bonnetrouge.rhymetime.commons.ViewModelFactory
import com.bonnetrouge.rhymetime.ext.*
import com.bonnetrouge.rhymetime.viewmodels.SingleWordViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_single_word.*
import javax.inject.Inject

class SingleWordFragment : DaggerFragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory

    private val singleWordViewModel by lazyAndroid {
        ViewModelProviders.of(this, viewModelFactory).get(SingleWordViewModel::class.java)
    }

    private val rhymesAdapter by lazyAndroid { SimpleWordAdapter(R.drawable.bg_outline_rhymes) }
    private val nearRhymesAdapter by lazyAndroid { SimpleWordAdapter(R.drawable.bg_outline_near_rhymes) }
    private val homophonesAdapter by lazyAndroid { SimpleWordAdapter(R.drawable.bg_outline_homophones) }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        singleWordViewModel.updateCurrentWord(arguments?.getString(WORD_KEY)!!)
        singleWordViewModel.wordDataLiveData.observe(this) {
            it?.let {
                wordTitle?.text = it.word
                it.rhymes?.letNonEmpty {
                    rhymesAdapter.submitList(it)
                    rhymeCard.visible()
                }
                it.nearRhymes?.letNonEmpty {
                    nearRhymesAdapter.submitList(it)
                    nearRhymesCard.visible()
                }
                it.homophones?.letNonEmpty {
                    homophonesAdapter.submitList(it)
                    homophonesCard.visible()
                }
                val transition = TransitionSet().apply {
                    duration = 500L
                    ordering = ORDERING_SEQUENTIAL
                    addTransition(ChangeBounds())
                    addTransition(Slide())
                }
                TransitionManager.beginDelayedTransition(root, transition)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_single_word, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViews()
        favoriteFab.setOnClickListener {
            Log.d("Quman", "favorite fab touched")
        }
    }

    private fun setupRecyclerViews() {
        rhymeCard.gone()
        nearRhymesCard.gone()
        homophonesCard.gone()
        with (rhymeRV) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = rhymesAdapter
        }
        with (nearRhymesRV) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = nearRhymesAdapter
        }
        with (homophonesRV) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = homophonesAdapter
        }
    }

    companion object {
        fun getInstance(word: String): SingleWordFragment {
            return SingleWordFragment().apply {
                arguments = Bundle().apply {
                    putString(WORD_KEY, word)
                }
            }
        }

        private const val WORD_KEY = "word_key"
        val TAG = SingleWordFragment::class.java.name
    }
}
