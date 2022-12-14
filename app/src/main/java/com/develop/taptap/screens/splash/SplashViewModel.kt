package com.develop.taptap.screens.splash

import androidx.compose.runtime.mutableStateOf
import com.develop.taptap.screens.TapTapViewModel
import com.develop.taptap.service.AuthService
import com.develop.taptap.service.ConfigurationService
import com.develop.taptap.service.LogService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject  constructor(
    configurationService : ConfigurationService,
    private val authService : AuthService,
    logService : LogService
) : TapTapViewModel(logService) {
    val showError = mutableStateOf(false)

    init {
        launchCatching { configurationService.fetchConfiguration() }
    }

    fun onAppStart(openAndPopUp : (String,String)->Unit) {
        showError.value = false
        if(authService.hasUser) {
            openAndPopUp("main","")
        } else {
            openAndPopUp("auth","")
        }
    }

}

