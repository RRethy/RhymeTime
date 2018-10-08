package com.bonnetrouge.rhymetime.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment

class SingleWordFragment : DaggerFragment() {

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.d("Quman", arguments?.getString(WORD_KEY))
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
