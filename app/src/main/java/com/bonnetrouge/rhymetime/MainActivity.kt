package com.bonnetrouge.rhymetime

import android.os.Bundle
import com.bonnetrouge.rhymetime.ext.fragmentTransaction
import com.bonnetrouge.rhymetime.fragments.ChallengeFragment
import com.bonnetrouge.rhymetime.fragments.SandboxFragment
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            fragmentTransaction {
                add(R.id.fragmentContainer, ChallengeFragment())
            }
        }
    }
}
