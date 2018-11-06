package com.bonnetrouge.rhymetime.adapters

import android.support.v7.recyclerview.extensions.AsyncListDiffer
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bonnetrouge.rhymetime.R
import com.bonnetrouge.rhymetime.ext.lazyAndroid
import com.bonnetrouge.rhymetime.listeners.RVClickListener
import com.bonnetrouge.rhymetime.models.WordList
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_simple_word_list.*

class SimpleListsAdapter constructor(
        val clickListener: RVClickListener<String>
) : ListAdapter<WordList, SimpleListsAdapter.SimpleWordListVH>(WordList.DIFF_CALLBACK) {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SimpleWordListVH {
        return SimpleWordListVH(LayoutInflater.from(p0.context).inflate(R.layout.view_holder_simple_word_list, p0, false))
    }

    override fun onBindViewHolder(p0: SimpleWordListVH, p1: Int) {
        p0.bind()
    }

    inner class SimpleWordListVH(
            override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        private val wordsAdapter by lazyAndroid { SimpleWordAdapter(null, clickListener) }
        private val linearLayoutManager by lazyAndroid { LinearLayoutManager(containerView.context, LinearLayoutManager.HORIZONTAL, false) }

        init {
            with (wordsRV) {
                layoutManager = linearLayoutManager
                adapter = wordsAdapter
            }
        }

        fun bind() {
            wordsAdapter.textBg = getItem(adapterPosition).bg
            wordsAdapter.submitList(getItem(adapterPosition).words)
        }
    }
}
