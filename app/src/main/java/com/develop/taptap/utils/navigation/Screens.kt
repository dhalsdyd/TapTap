package com.develop.taptap.utils.navigation

sealed class Screens (val route : String) {
    object Login : Screens("login")
}