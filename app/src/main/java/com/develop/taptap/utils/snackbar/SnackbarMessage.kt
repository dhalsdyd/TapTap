package com.develop.taptap.utils.snackbar

import android.content.res.Resources
import androidx.annotation.StringRes

sealed class SnackbarMessage {
    class StringSnackbar(val message:String) : SnackbarMessage()
    class ResourceSnackbar(@StringRes val message:Int): SnackbarMessage()

    companion object {
        fun SnackbarMessage.toMessage(resource : Resources): String {
return when(this) {
                is StringSnackbar -> message
                is ResourceSnackbar -> resource.getString(message)
            }
        }

        fun Throwable.toSnackbarMessage(): SnackbarMessage {

            val message = this.message.orEmpty()
            return if (message.isNotBlank()) StringSnackbar(message) else ResourceSnackbar(com.develop.taptap.R.string.error)
        }
    }
}