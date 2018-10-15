package com.bonnetrouge.rhymetime.fragments

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.transition.ChangeBounds
import android.support.transition.Slide
import android.support.transition.TransitionManager
import android.support.transition.TransitionSet
import android.support.transition.TransitionSet.ORDERING_SEQUENTIAL
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bonnetrouge.rhymetime.R
import com.bonnetrouge.rhymetime.activities.MainActivity
import com.bonnetrouge.rhymetime.adapters.SimpleWordAdapter
import com.bonnetrouge.rhymetime.commons.ViewModelFactory
import com.bonnetrouge.rhymetime.ext.*
import com.bonnetrouge.rhymetime.listeners.RVClickListener
import com.bonnetrouge.rhymetime.viewmodels.SingleWordViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_single_word.*
import javax.inject.Inject

class SingleWordFragment : DaggerFragment(), RVClickListener<String> {

    @Inject lateinit var viewModelFactory: ViewModelFactory

    private val singleWordViewModel by lazyAndroid {
        ViewModelProviders.of(this, viewModelFactory).get(SingleWordViewModel::class.java)
    }

    private val rhymesAdapter by lazyAndroid { SimpleWordAdapter(R.drawable.bg_outline_rhymes, this) }
    private val nearRhymesAdapter by lazyAndroid { SimpleWordAdapter(R.drawable.bg_outline_near_rhymes, this) }
    private val homophonesAdapter by lazyAndroid { SimpleWordAdapter(R.drawable.bg_outline_homophones, this) }

    private val word by lazyAndroid { arguments?.getString(WORD_KEY)!! }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        singleWordViewModel.getWordRhymes(word).observe(this) {
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
        singleWordViewModel.isWordFavorite(word).observe(this) {
            if (it == null) return@observe
            if (it) {
                favoriteFab.setImageResource(R.drawable.ic_star_filled_white_24dp)
                favoriteFab.setOnClickListener {
                    singleWordViewModel.removeFavorite(word)
                }
            } else {
                favoriteFab.setImageResource(R.drawable.ic_star_border_white_24dp)
                favoriteFab.setOnClickListener {
                    singleWordViewModel.addFavorite(word)
                }
            }
        }
        return inflater.inflate(R.layout.fragment_single_word, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViews()
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).showBackButton()
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

    override fun onItemClick(data: String, index: Int) {
        (activity as AppCompatActivity).fragmentTransaction(true) {
            replace(R.id.fragmentContainer, SingleWordFragment.getInstance(data), SingleWordFragment.TAG)
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
