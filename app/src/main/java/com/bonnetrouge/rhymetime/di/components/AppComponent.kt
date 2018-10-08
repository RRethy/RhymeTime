package com.bonnetrouge.rhymetime.di.components

import com.bonnetrouge.rhymetime.RhymeTimeApp
import com.bonnetrouge.rhymetime.di.modules.AppModule
import com.bonnetrouge.rhymetime.di.modules.MainActivityModule
import com.bonnetrouge.rhymetime.di.modules.RepoModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    MainActivityModule::class,
    RepoModule::class
])
interface AppComponent {
    fun inject(app: RhymeTimeApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: RhymeTimeApp): Builder

        fun build(): AppComponent
    }
}
