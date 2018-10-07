package com.bonnetrouge.rhymetime.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bonnetrouge.rhymetime.R
import dagger.android.support.DaggerFragment

class SandboxFragment : DaggerFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sandbox, container, false)
    }

    companion object {
        fun getInstance(): SandboxFragment {
            return SandboxFragment()
        }

        val TAG = SandboxFragment::class.java.name!!
    }
}
