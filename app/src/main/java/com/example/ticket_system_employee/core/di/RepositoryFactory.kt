package com.example.ticket_system_employee.core.di

import android.content.Context
import com.example.ticket_system_employee.data.repositoryImp.LoginRepositoryImp
import com.example.ticket_system_employee.domain.repository.login.LoginRepository

object RepositoryFactory {
    fun loginRepository(context: Context) = LoginRepositoryImp.getInstance(context)

}