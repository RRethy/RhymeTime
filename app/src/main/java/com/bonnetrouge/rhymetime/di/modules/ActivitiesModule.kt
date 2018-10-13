package com.bonnetrouge.rhymetime.di.modules

import android.app.Activity
import com.bonnetrouge.rhymetime.activities.MainActivity
import com.bonnetrouge.rhymetime.activities.SingleWordActivity
import com.bonnetrouge.rhymetime.di.components.MainActivitySubcomponent
import com.bonnetrouge.rhymetime.di.components.SingleWordActivitySubcomponent
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = [MainActivitySubcomponent::class])
abstract class MainActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun bindMainActivityInjectorFactory(builder: MainActivitySubcomponent.Builder): AndroidInjector.Factory<out Activity>
}

@Module(subcomponents = [SingleWordActivitySubcomponent::class])
abstract class SingleWordActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(SingleWordActivity::class)
    abstract fun bindSingleWordActivityInjectorFactory(builder: SingleWordActivitySubcomponent.Builder): AndroidInjector.Factory<out Activity>
}
