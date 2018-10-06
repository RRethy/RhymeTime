package com.bonnetrouge.rhymetime.di.components

import com.bonnetrouge.rhymetime.MainActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent()
interface MainActivitySubcomponent : AndroidInjector<MainActivity> {
    @Subcomponent.Builder abstract class Builder : AndroidInjector.Builder<MainActivity>()
}
