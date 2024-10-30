package com.example.mynews.di

import android.app.Application
import com.example.mynews.data.manager.LocalUserManagerImpl
import com.example.mynews.domain.manager.LocalUserManager
import com.example.mynews.domain.manager.usecases.AppEntryUseCases
import com.example.mynews.domain.manager.usecases.ReadAppEntry
import com.example.mynews.domain.manager.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ):AppEntryUseCases = AppEntryUseCases(
        readAppEntry=ReadAppEntry(localUserManager),
        saveAppEntry=SaveAppEntry(localUserManager))
}