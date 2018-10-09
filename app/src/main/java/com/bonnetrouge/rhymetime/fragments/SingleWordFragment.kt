package com.bonnetrouge.rhymetime.fragments

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bonnetrouge.rhymetime.commons.ViewModelFactory
import com.bonnetrouge.rhymetime.ext.lazyAndroid
import com.bonnetrouge.rhymetime.ext.observe
import com.bonnetrouge.rhymetime.viewmodels.SingleWordViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SingleWordFragment : DaggerFragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory

    private val singleWordViewModel by lazyAndroid {
        ViewModelProviders.of(this, viewModelFactory).get(SingleWordViewModel::class.java)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        singleWordViewModel.updateCurrentWord(arguments?.getString(WORD_KEY)!!)
        singleWordViewModel.wordDataLiveData.observe(this) {
            Log.d("Quman", it.toString())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
