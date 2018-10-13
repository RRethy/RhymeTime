package com.bonnetrouge.rhymetime.adapters

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bonnetrouge.rhymetime.R
import com.bonnetrouge.rhymetime.ext.stringDiffCallback
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_simple_word.*

class SimpleWordAdapter : ListAdapter<String, SimpleWordAdapter.SimpleWord>(stringDiffCallback) {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SimpleWord {
        return SimpleWord(LayoutInflater.from(p0.context).inflate(R.layout.view_holder_simple_word, p0, false))
    }

    override fun onBindViewHolder(p0: SimpleWord, p1: Int) {
        p0.bind()
    }

    inner class SimpleWord(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind() {
            wordText.text = getItem(adapterPosition)
        }
    }
}
