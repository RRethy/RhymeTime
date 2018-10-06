package com.bonnetrouge.rhymetime.ext

import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

inline fun AppCompatActivity.fragmentTransaction(addToBackStack: Boolean = false, tag: String? = null, swapInfo: FragmentTransaction.() -> Unit) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.swapInfo()
    if (addToBackStack) {
        transaction.addToBackStack(tag)
    }
    transaction.commit()
}
