package com.example.ticket_system_employee.core

import android.app.Application
import com.example.ticket_system_employee.core.di.Module

class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        Module(this).loginUseCases.getContext()
    }
}