package com.develop.taptap.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develop.taptap.service.LogService
import com.develop.taptap.utils.snackbar.SnackbarManager
import com.develop.taptap.utils.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class TapTapViewModel(private val logService: LogService):ViewModel() {
    fun launchCatching(snackbar:Boolean = true,block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch(
        CoroutineExceptionHandler { coroutineContext, throwable -> if(snackbar) {
            SnackbarManager.showMessage(throwable.toSnackbarMessage())
        }
        logService.logNonFatalCrash(throwable)
                                  }, block = block)
}