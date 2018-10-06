package com.bonnetrouge.rhymetime.di.modules

import com.bonnetrouge.rhymetime.di.components.ChallengeFragmentSubcomponent
import com.bonnetrouge.rhymetime.di.components.SandboxFragmentSubcomponent
import com.bonnetrouge.rhymetime.fragments.ChallengeFragment
import com.bonnetrouge.rhymetime.fragments.SandboxFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [
    ChallengeFragmentSubcomponent::class,
    SandboxFragmentSubcomponent::class
])
abstract class FragmentsModule {
    @Binds
    @IntoMap
    @FragmentKey(ChallengeFragment::class)
    abstract fun bindChallengeFragmentInjectorFactory(builder: ChallengeFragmentSubcomponent.Builder):
            AndroidInjector.Factory<out android.support.v4.app.Fragment>


    @Binds
    @IntoMap
    @FragmentKey(SandboxFragment::class)
    abstract fun bindSandboxFragmentInjectorFactory(builder: SandboxFragmentSubcomponent.Builder):
            AndroidInjector.Factory<out android.support.v4.app.Fragment>
}
