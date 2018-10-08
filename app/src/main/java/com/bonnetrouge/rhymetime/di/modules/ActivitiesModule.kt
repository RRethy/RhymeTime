package com.bonnetrouge.rhymetime.di.modules

import android.app.Activity
import com.bonnetrouge.rhymetime.activities.MainActivity
import com.bonnetrouge.rhymetime.di.components.MainActivitySubcomponent
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = [MainActivitySubcomponent::class],
        includes = [ViewModelModule::class])
abstract class MainActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun bindMainActivityInjectorFactory(builder: MainActivitySubcomponent.Builder): AndroidInjector.Factory<out Activity>
}
