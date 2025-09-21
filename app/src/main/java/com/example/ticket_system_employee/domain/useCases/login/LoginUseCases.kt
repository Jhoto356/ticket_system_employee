package com.example.ticket_system_employee.domain.useCases.login

import android.content.Context
import com.example.ticket_system_employee.domain.repository.login.LoginRepository

class LoginUseCases(private val loginRepository: LoginRepository) {
    fun getContext(): Context = loginRepository.getContext()
}