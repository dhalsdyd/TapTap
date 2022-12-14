package com.develop.taptap.service

interface ConfigurationService {
    suspend fun fetchConfiguration():Boolean
    val isShowTaskEditButtonConfig: Boolean
}