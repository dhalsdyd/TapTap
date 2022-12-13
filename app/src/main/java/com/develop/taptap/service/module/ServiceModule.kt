package com.develop.taptap.service.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds abstract fun provideAccountService()
    @Binds abstract fun provideAuthService()
    @Binds abstract fun provideDatabaseService()
    @Binds abstract fun provideGameService()
}