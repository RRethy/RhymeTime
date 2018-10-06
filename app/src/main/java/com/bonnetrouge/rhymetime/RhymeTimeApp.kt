package com.bonnetrouge.rhymetime

import android.app.Activity
import android.app.Application
import com.bonnetrouge.rhymetime.di.components.AppComponent
import com.bonnetrouge.rhymetime.di.components.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class RhymeTimeApp : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    lateinit var appComponent: AppComponent
        private set

    override fun activityInjector() = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        createAppComponent()
        appComponent.inject(this)
    }

    private fun createAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build()
    }
}