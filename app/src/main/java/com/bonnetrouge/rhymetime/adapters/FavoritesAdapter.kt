package com.bonnetrouge.rhymetime.adapters

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bonnetrouge.rhymetime.R
import com.bonnetrouge.rhymetime.listeners.RVClickListener
import com.bonnetrouge.rhymetime.models.WordFavorite
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_favorite_word.*

class FavoritesAdapter(
        val listener: RVClickListener<WordFavorite>
) : ListAdapter<WordFavorite, FavoritesAdapter.FavoritesViewHolder>(WordFavorite.DIFF_CALLBACK) {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FavoritesViewHolder {
        return FavoritesViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.view_holder_favorite_word, p0, false))
    }

    override fun onBindViewHolder(p0: FavoritesViewHolder, p1: Int) {
        p0.bind()
    }

    inner class FavoritesViewHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            containerView.setOnClickListener { listener.onItemClick(getItem(adapterPosition), adapterPosition) }
        }

        fun bind() {
            word.text = getItem(adapterPosition).word
        }
    }
}