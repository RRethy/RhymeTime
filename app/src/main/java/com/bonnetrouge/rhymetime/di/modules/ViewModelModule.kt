package com.bonnetrouge.rhymetime.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.bonnetrouge.rhymetime.commons.ViewModelFactory
import com.bonnetrouge.rhymetime.di.ViewModelKey
import com.bonnetrouge.rhymetime.viewmodels.FavoritesViewModel
import com.bonnetrouge.rhymetime.viewmodels.SandboxViewModel
import com.bonnetrouge.rhymetime.viewmodels.SearchViewModel
import com.bonnetrouge.rhymetime.viewmodels.SingleWordViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SingleWordViewModel::class)
    abstract fun bindSingleWordViewModel(singleWordViewModel: SingleWordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    abstract fun bindFavoritesViewModel(favoritesViewModel: FavoritesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SandboxViewModel::class)
    abstract fun bindSandboxViewModel(sandboxViewModel: SandboxViewModel): ViewModel
}