package com.develop.taptap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.develop.taptap.utils.navigation.Screens
import com.develop.taptap.screens.login.LoginScreen
import com.develop.taptap.ui.theme.TapTapTheme
import com.develop.taptap.utils.GoogleLoginUtils

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TapTap {

            }
        }
    }
}

@Composable
fun TapTap(content: @Composable () -> Unit) {

    TapTapTheme {

        val navController = rememberNavController()
        val scope = rememberCoroutineScope()
        val scaffoldState = rememberScaffoldState()
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "TapTap") },
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = Color.White,
                    elevation = 12.dp
                )

            },
            scaffoldState = scaffoldState,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
            NavHost(
                navController = navController,
                startDestination = Screens.Login.route,
                modifier = Modifier.fillMaxSize()
            ) {
                composable(Screens.Login.route) {
                    LoginScreen {
                        GoogleLoginUtils().googleLogin()
                    }
                }


            }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TapTapTheme {
        Greeting("Android")
    }
}