package com.grigor.myapplication.application

import android.app.Application
import com.grigor.myapplication.di.repositoryModule
import com.grigor.myapplication.di.servicesModule
import com.grigor.myapplication.di.useCaseModule
import com.grigor.myapplication.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GeoMotiveTestApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin()= startKoin {
        androidLogger()
        androidContext(this@GeoMotiveTestApp)
        modules(viewModelsModule,useCaseModule,repositoryModule, servicesModule)
    }
}