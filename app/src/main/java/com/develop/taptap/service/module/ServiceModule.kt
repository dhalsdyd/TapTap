package com.develop.taptap.service.module

import com.develop.taptap.service.AuthService
import com.develop.taptap.service.ConfigurationService
import com.develop.taptap.service.LogService
import com.develop.taptap.service.impl.AuthServiceImpl
import com.develop.taptap.service.impl.ConfigurationServiceImpl
import com.develop.taptap.service.impl.LogServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds abstract fun provideAuthService(impl: AuthServiceImpl): AuthService
    @Binds abstract fun provideLogService(impl: LogServiceImpl): LogService
    @Binds abstract fun provideConfigurationService(impl: ConfigurationServiceImpl): ConfigurationService
}