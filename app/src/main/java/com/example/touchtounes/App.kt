package com.example.touchtounes

import android.app.Application
import com.example.touchtounes.di.modules.appModule
import com.example.touchtounes.di.modules.repositoryModule
import com.example.touchtounes.di.modules.serviceModule
import com.example.touchtounes.di.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, repositoryModule, viewModelModule, serviceModule))
        }
    }
}
