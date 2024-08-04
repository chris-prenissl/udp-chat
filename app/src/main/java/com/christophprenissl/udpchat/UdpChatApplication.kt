package com.christophprenissl.udpchat

import android.app.Application
import com.christophprenissl.udpchat.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class UdpChatApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@UdpChatApplication)
            modules(appModule)
        }
    }
}