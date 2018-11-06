package com.bonnetrouge.rhymetime.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bonnetrouge.rhymetime.R
import com.bonnetrouge.rhymetime.adapters.SimpleWordAdapter
import com.bonnetrouge.rhymetime.commons.ViewModelFactory
import com.bonnetrouge.rhymetime.ext.lazyAndroid
import com.bonnetrouge.rhymetime.listeners.RVClickListener
import com.bonnetrouge.rhymetime.viewmodels.SandboxViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_sandbox.*
import javax.inject.Inject

class SandboxFragment : DaggerFragment(), RVClickListener<String> {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val sandboxViewModel by lazyAndroid {
        ViewModelProviders.of(this, viewModelFactory).get(SandboxViewModel::class.java)
    }

    private val rhymesAdapter by lazyAndroid { SimpleWordAdapter(R.color.sandbox_rhymes, this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sandbox, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with (rhymesList) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = rhymesAdapter
        }
    }

    override fun onItemClick(data: String, index: Int) {
        Log.d("Quman", data)
    }

    companion object {
        fun getInstance(): SandboxFragment {
            return SandboxFragment()
        }

        val TAG = SandboxFragment::class.java.name!!
    }
}
