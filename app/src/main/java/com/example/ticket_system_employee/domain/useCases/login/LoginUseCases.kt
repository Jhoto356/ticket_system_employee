package com.example.ticket_system_employee.domain.useCases.login

import android.content.Context
import com.example.ticket_system_employee.domain.repository.login.LoginRepository
import com.example.ticket_system_employee.domain.result.login.LoginResult
import com.example.ticket_system_employee.presentation.commons.models.EmployeeToLogin

class LoginUseCases(private val loginRepository: LoginRepository) {
    fun getContext(): Context = loginRepository.getContext()
    fun verifyInitialData() = loginRepository.validateInitialData()
    fun validateLogin(employeeToLogin: EmployeeToLogin): LoginResult = loginRepository.validateLogion(employeeToLogin)
}