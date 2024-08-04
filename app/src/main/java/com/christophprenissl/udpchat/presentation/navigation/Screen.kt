package com.christophprenissl.udpchat.presentation.navigation

sealed class Screen(val route: String) {
    data object LoginScreen : Screen("login")
    data object ChatScreen : Screen("chat")
}