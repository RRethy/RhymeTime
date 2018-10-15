package com.bonnetrouge.rhymetime.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bonnetrouge.rhymetime.R
import com.bonnetrouge.rhymetime.activities.MainActivity
import com.bonnetrouge.rhymetime.adapters.FavoritesAdapter
import com.bonnetrouge.rhymetime.commons.SimpleItemTouchHelper
import com.bonnetrouge.rhymetime.commons.ViewModelFactory
import com.bonnetrouge.rhymetime.ext.fragmentTransaction
import com.bonnetrouge.rhymetime.ext.lazyAndroid
import com.bonnetrouge.rhymetime.ext.observe
import com.bonnetrouge.rhymetime.listeners.RVClickListener
import com.bonnetrouge.rhymetime.models.WordFavorite
import com.bonnetrouge.rhymetime.viewmodels.FavoritesViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_favorites.*
import javax.inject.Inject

class FavoritesFragment : DaggerFragment(), RVClickListener<WordFavorite>, SimpleItemTouchHelper.OnItemChangedListener {

    @Inject lateinit var viewModelFactory: ViewModelFactory

    private val favoritesViewModel by lazyAndroid {
        ViewModelProviders.of(this, viewModelFactory).get(FavoritesViewModel::class.java)
    }

    private val favoritesAdapter by lazyAndroid { FavoritesAdapter(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        favoritesViewModel.favorites.observe(this) {
            favoritesAdapter.submitList(it)
        }
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lmanager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        wordList.layoutManager = lmanager
        wordList.adapter = favoritesAdapter
        wordList.addItemDecoration(DividerItemDecoration(context, lmanager.orientation))
        SimpleItemTouchHelper(this).attachToRecyclerView(wordList)
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).hideBackButton()
    }

    override fun onItemClick(data: WordFavorite, index: Int) {
        (activity as AppCompatActivity).fragmentTransaction(true) {
            replace(R.id.fragmentContainer, SingleWordFragment.getInstance(data.word), SingleWordFragment.TAG)
        }
    }

    override fun onItemStateChanged(position: Int, direction: Int) {
        favoritesViewModel.removeFavorite(favoritesAdapter.getItemAtPosition(position).word)
    }

    companion object {
        fun getInstance(): FavoritesFragment {
            return FavoritesFragment()
        }
        val TAG = FavoritesFragment::class.java.name!!
    }
}