package com.bonnetrouge.rhymetime.di.modules

import android.arch.persistence.room.Room
import com.bonnetrouge.rhymetime.RhymeTimeApp
import com.bonnetrouge.rhymetime.room.RhymeTimeDatabase
import com.bonnetrouge.rhymetime.room.daos.WordsInfoDao
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

    @Singleton
    @Provides
    fun provideDatabase(app: RhymeTimeApp): RhymeTimeDatabase {
        return Room.databaseBuilder(app, RhymeTimeDatabase::class.java, "Choice.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideChoicesDao(db: RhymeTimeDatabase): WordsInfoDao {
        return db.wordsInfoDao()
    }
}
