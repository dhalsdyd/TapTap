package com.develop.taptap

import android.content.res.Resources
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.develop.taptap.screens.login.LoginScreen
import com.develop.taptap.ui.theme.TapTapTheme
import com.develop.taptap.utils.navigation.Screens
import com.develop.taptap.utils.snackbar.SnackbarManager
import kotlinx.coroutines.CoroutineScope

@Composable
@ExperimentalMaterialApi
fun TapTapApp() {
    TapTapTheme() {
        Surface(color = MaterialTheme.colors.background) {
            val appState = rememberAppState()
            Scaffold(
                snackbarHost = {
                    SnackbarHost(
                        hostState = it,
                        modifier = Modifier.padding(8.dp),
                        snackbar = { data ->
                            Snackbar(
                                data,
                                contentColor = MaterialTheme.colors.onPrimary
                            )
                        }
                    )}, scaffoldState = appState.scaffoldState)
            { innerPadding ->
                NavHost(navController = appState.navController, startDestination = Screens.Login.route, modifier = Modifier.padding(innerPadding)) {
TapTapGraph(appState)

                }
            }

    }
}
}

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    snackbarManager : SnackbarManager = SnackbarManager,
    resources : Resources = resources(),
    coroutinScope : CoroutineScope = rememberCoroutineScope(),
) = remember(navController, scaffoldState, snackbarManager, resources, coroutinScope) {
    TapTapState(navController, scaffoldState, snackbarManager, resources, coroutinScope)
}

@Composable
@ReadOnlyComposable
fun resources():Resources {
    return LocalContext.current.resources
}

@ExperimentalMaterialApi
fun NavGraphBuilder.TapTapGraph(appState : TapTapState) {
    composable(Screens.Login.route) {
        LoginScreen()
    }
}