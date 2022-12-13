package com.develop.taptap.navigation

sealed class Screens (val route : String) {
    object Login : Screens("login")
}