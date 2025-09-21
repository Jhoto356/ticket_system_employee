package com.example.ticket_system_employee.data.repositoryImp

import android.content.Context
import com.example.ticket_system_employee.domain.repository.login.LoginRepository
import com.example.ticket_system_employee.domain.result.login.LoginResult
import com.example.ticket_system_employee.presentation.commons.models.EmployeeToLogin
import com.example.ticket_system_employee.R

class LoginRepositoryImp(private val context: Context): LoginRepository {
    companion object {
        private val instance: LoginRepositoryImp ?= null
        fun getInstance(context: Context): LoginRepositoryImp {
            if (instance != null) {
                return instance
            }
            return LoginRepositoryImp(context)
        }

    }

    override fun getContext(): Context { return context }

    override fun validateLogion(employee: EmployeeToLogin): LoginResult {
        return try {
            LoginResult.SuccessLogin()
        } catch (e: Exception) {
            val message = context.getString(R.string.txt_error_login)
            LoginResult.ErrorLogin(message)
        }

    }
}