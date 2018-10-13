package com.bonnetrouge.rhymetime.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import com.bonnetrouge.rhymetime.R
import dagger.android.DaggerActivity
import dagger.android.support.DaggerAppCompatActivity

class SingleWordActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_word)
    }

    companion object {
        fun navigate(context: Context) {
            val intent = Intent(context, SingleWordActivity::class.java)
            context.startActivity(intent)
        }
    }
}