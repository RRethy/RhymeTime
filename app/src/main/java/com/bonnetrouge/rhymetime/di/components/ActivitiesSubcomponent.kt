package com.bonnetrouge.rhymetime.di.components

import com.bonnetrouge.rhymetime.activities.MainActivity
import com.bonnetrouge.rhymetime.activities.SingleWordActivity
import com.bonnetrouge.rhymetime.di.modules.FragmentsModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [
    FragmentsModule::class
])
interface MainActivitySubcomponent : AndroidInjector<MainActivity> {
    @Subcomponent.Builder abstract class Builder : AndroidInjector.Builder<MainActivity>()
}

@Subcomponent(modules = [])
interface SingleWordActivitySubcomponent : AndroidInjector<SingleWordActivity> {
    @Subcomponent.Builder abstract class Builder : AndroidInjector.Builder<SingleWordActivity>()
}
