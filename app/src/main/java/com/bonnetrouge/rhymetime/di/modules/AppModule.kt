package com.bonnetrouge.rhymetime.di.modules

import com.bonnetrouge.rhymetime.services.DatamuseService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module()
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api.datamuse.com/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideDatamuseService(retrofit: Retrofit): DatamuseService {
        return retrofit.create(DatamuseService::class.java)
    }
}
