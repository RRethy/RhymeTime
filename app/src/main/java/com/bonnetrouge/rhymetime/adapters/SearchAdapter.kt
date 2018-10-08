package com.bonnetrouge.rhymetime.adapters

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bonnetrouge.rhymetime.R
import com.bonnetrouge.rhymetime.models.Suggestion
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_search.*

class SearchAdapter : ListAdapter<Suggestion, SearchAdapter.SearchViewHolder>(Suggestion.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_search, parent, false))
    }

    override fun onBindViewHolder(viewHolder: SearchViewHolder, position: Int) = viewHolder.bind(getItem(position))

    inner class SearchViewHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(suggestion: Suggestion) {
            result.text = suggestion.word
        }
    }
}
