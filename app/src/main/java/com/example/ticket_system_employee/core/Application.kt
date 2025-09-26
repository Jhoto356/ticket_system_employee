package com.example.ticket_system_employee.core

import android.app.Application
import com.example.ticket_system_employee.core.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level

class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(applicationContext)
            modules(
                appModule,
                dataBaseModule,
                dataSourcesModule,
                repositoryImpModule,
                useCaseModule,
                viewModelModule
            )
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }

}