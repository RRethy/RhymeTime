package com.bonnetrouge.rhymetime.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment

class ChallengeFragment : DaggerFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    companion object {
        fun getInstance(): ChallengeFragment {
            return ChallengeFragment()
        }

        val TAG = ChallengeFragment::class.java.name
    }
}