package com.bonnetrouge.rhymetime.activities

import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import com.bonnetrouge.rhymetime.R
import com.bonnetrouge.rhymetime.ext.lazyAndroid
import com.bonnetrouge.rhymetime.ext.swapFragment
import com.bonnetrouge.rhymetime.fragments.ChallengeFragment
import com.bonnetrouge.rhymetime.fragments.SandboxFragment
import com.bonnetrouge.rhymetime.fragments.SearchFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity() {

    private val challengeFragment by lazyAndroid { ChallengeFragment.getInstance() }
    private val sandboxFragment by lazyAndroid { SandboxFragment.getInstance() }
    private val searchFragment by lazyAndroid { SearchFragment.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) swapFragment(R.id.fragmentContainer, ChallengeFragment.TAG, challengeFragment)
        setupBottomDrawer()
    }

    private fun setupBottomDrawer() {
        BottomSheetBehavior.from(bottomDrawer).state = BottomSheetBehavior.STATE_HIDDEN
        appBar.setNavigationOnClickListener {
            BottomSheetBehavior.from(bottomDrawer).state = BottomSheetBehavior.STATE_EXPANDED
        }
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_challenges -> swapFragment(R.id.fragmentContainer, ChallengeFragment.TAG, challengeFragment)
                R.id.menu_search -> swapFragment(R.id.fragmentContainer, SearchFragment.TAG, searchFragment)
                R.id.menu_sandbox -> swapFragment(R.id.fragmentContainer, SandboxFragment.TAG, sandboxFragment)
            }
            BottomSheetBehavior.from(bottomDrawer).state = BottomSheetBehavior.STATE_HIDDEN
            true
        }
    }
}