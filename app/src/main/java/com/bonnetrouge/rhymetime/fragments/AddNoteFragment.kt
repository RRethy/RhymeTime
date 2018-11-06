package com.bonnetrouge.rhymetime.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bonnetrouge.rhymetime.R
import com.bonnetrouge.rhymetime.adapters.SimpleListsAdapter
import com.bonnetrouge.rhymetime.adapters.SimpleWordAdapter
import com.bonnetrouge.rhymetime.commons.ViewModelFactory
import com.bonnetrouge.rhymetime.ext.lazyAndroid
import com.bonnetrouge.rhymetime.ext.observe
import com.bonnetrouge.rhymetime.listeners.RVClickListener
import com.bonnetrouge.rhymetime.viewmodels.AddNoteViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_add_node.*
import javax.inject.Inject

class AddNoteFragment : DaggerFragment(), RVClickListener<String> {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val addNoteViewModel by lazyAndroid {
        ViewModelProviders.of(this, viewModelFactory).get(AddNoteViewModel::class.java)
    }

    private val rhymesAdapter by lazyAndroid { SimpleListsAdapter(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        addNoteViewModel.getWordRhymes("you").observe(this) {
            rhymesAdapter.submitList(it)
        }
        return inflater.inflate(R.layout.fragment_add_node, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with (rhymesList) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = rhymesAdapter
            PagerSnapHelper().attachToRecyclerView(this)
        }
    }

    override fun onItemClick(data: String, index: Int) {
        Log.d("Quman", data)
    }

    companion object {
        fun getInstance(): AddNoteFragment {
            return AddNoteFragment()
        }

        val TAG = AddNoteFragment::class.java.name!!
    }
}
