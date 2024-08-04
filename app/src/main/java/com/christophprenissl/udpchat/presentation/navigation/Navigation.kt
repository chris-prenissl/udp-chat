package com.christophprenissl.udpchat.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.christophprenissl.udpchat.presentation.chat.ChatScreen
import com.christophprenissl.udpchat.presentation.chat.ChatViewModel
import com.christophprenissl.udpchat.presentation.login.LoginNavigationEvent
import com.christophprenissl.udpchat.presentation.login.LoginScreen
import com.christophprenissl.udpchat.presentation.login.LoginViewModel
import com.christophprenissl.udpchat.presentation.util.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        composable(route = Screen.LoginScreen.route) {
            val viewModel = koinViewModel<LoginViewModel>()
            ObserveAsEvents(flow = viewModel.navigationEvent) { event ->
                when (event) {
                    is LoginNavigationEvent.Login -> navController.navigate(Screen.ChatScreen.route)
                }
            }
            val state = viewModel.state.collectAsStateWithLifecycle()
            LoginScreen(state = state.value, onEvent = viewModel::onEvent)
        }
        composable(route = Screen.ChatScreen.route) {
            val viewModel = koinViewModel<ChatViewModel>()
            val state = viewModel.state.collectAsStateWithLifecycle()
            ChatScreen(state = state.value, onEvent = viewModel::onEvent)
        }
    }
}