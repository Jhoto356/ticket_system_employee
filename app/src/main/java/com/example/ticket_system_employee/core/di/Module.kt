package com.example.ticket_system_employee.core.di

import android.content.Context
import com.example.ticket_system_employee.data.repositoryImp.LoginRepositoryImp
import com.example.ticket_system_employee.domain.repository.login.LoginRepository
import com.example.ticket_system_employee.domain.useCases.login.LoginUseCases
import kotlin.reflect.KClass

class Module(private val context: Context) {
    val loginRepositoryImp: LoginRepository = RepositoryFactory.loginRepository(context)
    val loginUseCases: LoginUseCases = UseCasesFactory.loginUseCases(loginRepositoryImp)
}