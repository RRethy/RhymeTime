package com.bonnetrouge.rhymetime.ext

import android.support.v4.app.Fragment
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

fun AppCompatActivity.swapFragment(
        containerId: Int,
        tag: String,
        fragment: Fragment
) {
    val transaction = supportFragmentManager.beginTransaction()
    if (supportFragmentManager.findFragmentByTag(tag) == null) {
        transaction.add(containerId, fragment, tag)
    }
    transaction.show(fragment)
    transaction.commit()
}

/**
 * Faster lazy delegation for Android.
 * Warning: Only use for objects accessed on main thread
 */
fun <T> lazyAndroid(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)
