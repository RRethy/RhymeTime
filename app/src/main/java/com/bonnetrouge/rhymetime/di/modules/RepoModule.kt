package com.bonnetrouge.rhymetime.di.modules

import com.bonnetrouge.rhymetime.repositories.DatamuseRepo
import com.bonnetrouge.rhymetime.repositories.impls.DatamuseRepoImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepoModule {

    @Binds
    @Singleton
    abstract fun bindDatamuseRepo(datamuseRepo: DatamuseRepoImpl): DatamuseRepo
}