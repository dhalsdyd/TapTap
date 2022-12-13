package com.develop.taptap.service

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class UserStateViewModel : ViewModel() {
    var isLogin = mutableStateOf(false)
    var isBusy = mutableStateOf(false)

    suspend fun signIn() {
        isBusy.value = true
        delay(2500)
        isLogin.value = true

        isBusy.value = false
    }

    suspend fun signOut() {
        isBusy.value = true
        delay(2500)
        isLogin.value = false
        isBusy.value = false
    }
}

val UserState = compositionLocalOf<UserStateViewModel> { error("Not Found!") }