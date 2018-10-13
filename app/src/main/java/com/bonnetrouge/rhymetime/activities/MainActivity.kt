package com.bonnetrouge.rhymetime.activities

import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.Fragment
import com.bonnetrouge.rhymetime.R
import com.bonnetrouge.rhymetime.ext.fragmentTransaction
import com.bonnetrouge.rhymetime.ext.lazyAndroid
import com.bonnetrouge.rhymetime.fragments.ChallengeFragment
import com.bonnetrouge.rhymetime.fragments.SandboxFragment
import com.bonnetrouge.rhymetime.fragments.SearchFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity() {

    private val challengeFragment by lazyAndroid { ChallengeFragment.getInstance() }
    private val sandboxFragment by lazyAndroid { SandboxFragment.getInstance() }
    private val searchFragment by lazyAndroid { SearchFragment.getInstance() }

    private var showingBackButton = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) fragmentTransaction {
            add(R.id.fragmentContainer, searchFragment, SearchFragment.TAG)
        }
        else showingBackButton = savedInstanceState.getBoolean("backbuttonshowing")
        if (showingBackButton) {
            showBackButton()
        } else {
            hideBackButton()
        }
        setupBottomDrawer()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putBoolean("backbuttonshowing", showingBackButton)
        super.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        hideBackButton()
    }

    private fun setupBottomDrawer() {
        BottomSheetBehavior.from(bottomDrawer).state = BottomSheetBehavior.STATE_HIDDEN
        appBar.setNavigationOnClickListener {
            if (showingBackButton) {
                onBackPressed()
            } else {
                BottomSheetBehavior.from(bottomDrawer).state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_challenges -> prettySwapFragment(challengeFragment, ChallengeFragment.TAG)
                R.id.menu_search -> prettySwapFragment(searchFragment, SearchFragment.TAG)
                R.id.menu_sandbox -> prettySwapFragment(sandboxFragment, SandboxFragment.TAG)
            }
            BottomSheetBehavior.from(bottomDrawer).state = BottomSheetBehavior.STATE_HIDDEN
            true
        }
    }

    private fun prettySwapFragment(fragment: Fragment, tag: String) {
        fragmentTransaction {
            setCustomAnimations(R.anim.grow_in, R.anim.hide)
            replace(R.id.fragmentContainer, fragment, tag)
        }
    }

    fun showBackButton() {
        appBar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        showingBackButton = true
    }

    fun hideBackButton() {
        appBar.setNavigationIcon(R.drawable.ic_menu_white_24dp)
        showingBackButton = false
    }
}
