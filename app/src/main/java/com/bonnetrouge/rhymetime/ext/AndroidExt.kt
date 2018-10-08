package com.bonnetrouge.rhymetime.ext

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.bonnetrouge.rhymetime.R

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
        fragment: Fragment,
        addToBackStack: Boolean = false
) {
    val transaction = supportFragmentManager.beginTransaction()
    val oldFragment = supportFragmentManager.findFragmentById(containerId)
    if (supportFragmentManager.findFragmentByTag(tag) == null) {
        transaction.add(containerId, fragment, tag)
    }
    oldFragment?.let { transaction.hide(it) }
    transaction.setCustomAnimations(R.anim.grow_in, R.anim.hide)
    transaction.show(fragment)
    if (addToBackStack) {
        transaction.addToBackStack(tag)
    }
    transaction.commit()
}

/**
 * Faster lazy delegation for Android.
 * Warning: Only use for objects accessed on main thread
 */
fun <T> lazyAndroid(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)

/** Syntax helper to convert
 * data.observe(this, Observer<Int> { ... })
 * to
 * data.observe(this) { ... }
 */
fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: (T?) -> Unit) = observe(owner, Observer(observer))
