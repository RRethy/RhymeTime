package com.bonnetrouge.rhymetime.di.modules

import com.bonnetrouge.rhymetime.di.components.*
import com.bonnetrouge.rhymetime.fragments.*
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [
    SearchFragmentSubcomponent::class,
    SingleWordSubcomponent::class,
    FavoritesSubcomponent::class
])
abstract class FragmentsModule {
    @Binds
    @IntoMap
    @FragmentKey(SearchFragment::class)
    abstract fun bindSearchFragmentInjectorFactory(builder: SearchFragmentSubcomponent.Builder):
            AndroidInjector.Factory<out android.support.v4.app.Fragment>

    @Binds
    @IntoMap
    @FragmentKey(SingleWordFragment::class)
    abstract fun bindSingleWordFragment(build: SingleWordSubcomponent.Builder):
            AndroidInjector.Factory<out android.support.v4.app.Fragment>

    @Binds
    @IntoMap
    @FragmentKey(FavoritesFragment::class)
    abstract fun bindFavoritesFragment(build: FavoritesSubcomponent.Builder):
            AndroidInjector.Factory<out android.support.v4.app.Fragment>
}
