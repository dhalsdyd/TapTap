package com.develop.taptap

import android.content.res.Resources
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController
import com.develop.taptap.utils.snackbar.SnackbarManager
import kotlinx.coroutines.CoroutineScope

@Stable
class TapTapState (
    val navController: NavHostController,
    val scaffoldState: ScaffoldState,
    private val snackbarManager: SnackbarManager,
    private val resources: Resources,
    coroutineScope: CoroutineScope
        ){

}