package com.develop.taptap.service.impl

import androidx.core.os.trace
import com.develop.taptap.BuildConfig
import com.develop.taptap.R
import com.develop.taptap.service.ConfigurationService
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ConfigurationServiceImpl @Inject constructor() : ConfigurationService {

    private val remoteConfig get() = Firebase.remoteConfig

    init {
        if(BuildConfig.DEBUG) {
            val configSettings = remoteConfigSettings { minimumFetchIntervalInSeconds = 0 }
            remoteConfig.setConfigSettingsAsync(configSettings)
        }
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
    }



    override suspend fun fetchConfiguration(): Boolean = remoteConfig.fetchAndActivate().await()

    override val isShowTaskEditButtonConfig: Boolean
        get() = remoteConfig[SHOW_TASK_EDIT_BUTTON].asBoolean()

    companion object {
        private const val SHOW_TASK_EDIT_BUTTON = "show_task_edit_button"
        private const val FETCH_CONFIG_TRACE = "fetchConfig"

    }
}