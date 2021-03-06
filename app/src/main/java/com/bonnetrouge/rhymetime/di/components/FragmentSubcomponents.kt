package com.bonnetrouge.rhymetime.di.components

import com.bonnetrouge.rhymetime.fragments.*
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface SearchFragmentSubcomponent : AndroidInjector<SearchFragment> {
    @Subcomponent.Builder abstract class Builder : AndroidInjector.Builder<SearchFragment>()
}

@Subcomponent
interface SingleWordSubcomponent : AndroidInjector<SingleWordFragment> {
    @Subcomponent.Builder abstract class Builder : AndroidInjector.Builder<SingleWordFragment>()
}

@Subcomponent
interface FavoritesSubcomponent : AndroidInjector<FavoritesFragment> {
    @Subcomponent.Builder abstract class Builder : AndroidInjector.Builder<FavoritesFragment>()
}
