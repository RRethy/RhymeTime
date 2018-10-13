package com.bonnetrouge.rhymetime.fragments

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bonnetrouge.rhymetime.R
import com.bonnetrouge.rhymetime.adapters.FavoritesAdapter
import com.bonnetrouge.rhymetime.commons.ViewModelFactory
import com.bonnetrouge.rhymetime.ext.lazyAndroid
import com.bonnetrouge.rhymetime.ext.observe
import com.bonnetrouge.rhymetime.listeners.RVClickListener
import com.bonnetrouge.rhymetime.models.WordFavorite
import com.bonnetrouge.rhymetime.viewmodels.FavoritesViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_favorites.*
import javax.inject.Inject

class FavoritesFragment : DaggerFragment(), RVClickListener<WordFavorite> {

    @Inject lateinit var viewModelFactory: ViewModelFactory

    private val favoritesViewModel by lazyAndroid {
        ViewModelProviders.of(this, viewModelFactory).get(FavoritesViewModel::class.java)
    }

    private val favoritesAdapter by lazyAndroid { FavoritesAdapter(this) }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        favoritesViewModel.favorites.observe(this) {
            favoritesAdapter.submitList(it)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lmanager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        wordList.layoutManager = lmanager
        wordList.adapter = favoritesAdapter
        wordList.addItemDecoration(DividerItemDecoration(context, lmanager.orientation))
    }

    override fun onItemClick(data: WordFavorite, index: Int) {
        Log.d("Quman", data.toString())
    }

    companion object {
        fun getInstance(): FavoritesFragment {
            return FavoritesFragment()
        }
        val TAG = FavoritesFragment::class.java.name!!
    }
}