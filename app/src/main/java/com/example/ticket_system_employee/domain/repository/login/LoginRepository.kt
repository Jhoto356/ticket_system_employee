package com.example.ticket_system_employee.domain.repository.login

import android.content.Context
import com.example.ticket_system_employee.domain.result.login.LoginResult
import com.example.ticket_system_employee.presentation.commons.models.EmployeeToLogin

interface LoginRepository {
    fun getContext(): Context
    fun validateLogion(employee: EmployeeToLogin): LoginResult
    fun validateInitialData()

}