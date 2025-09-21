package com.example.ticket_system_employee.core.di

import com.example.ticket_system_employee.domain.repository.login.LoginRepository
import com.example.ticket_system_employee.domain.useCases.login.LoginUseCases

object UseCasesFactory {
    fun loginUseCases(loginRepository: LoginRepository) = LoginUseCases(loginRepository)
}