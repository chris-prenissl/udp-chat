package com.christophprenissl.udpchat.di

import com.christophprenissl.udpchat.data.repository.UdpChatRepository
import com.christophprenissl.udpchat.presentation.chat.ChatViewModel
import com.christophprenissl.udpchat.presentation.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { UdpChatRepository() }

    viewModel { LoginViewModel(get()) }

    viewModel { ChatViewModel(get()) }
}