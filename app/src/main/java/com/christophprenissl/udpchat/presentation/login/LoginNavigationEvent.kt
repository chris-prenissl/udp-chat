package com.christophprenissl.udpchat.presentation.login

sealed interface LoginNavigationEvent {
    data object Login: LoginNavigationEvent
}