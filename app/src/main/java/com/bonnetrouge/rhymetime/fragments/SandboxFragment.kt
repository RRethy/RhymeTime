package com.bonnetrouge.rhymetime.fragments

import dagger.android.support.DaggerFragment

class SandboxFragment : DaggerFragment() {

    companion object {
        fun getInstance(): SandboxFragment {
            return SandboxFragment()
        }

        val TAG = SandboxFragment::class.java.name
    }
}
