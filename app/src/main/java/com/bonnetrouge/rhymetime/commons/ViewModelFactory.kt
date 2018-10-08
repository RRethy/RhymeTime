package com.bonnetrouge.rhymetime.commons

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(
        private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        try {
            @Suppress("UNCHECKED_CAST")
            return creators[modelClass]?.get() as T
        } catch (e: Exception) {
            throw IllegalArgumentException("$modelClass cannot be created without a @Provides annotated method.")
        }
    }
}
