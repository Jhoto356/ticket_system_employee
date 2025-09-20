package com.example.ticket_system_employee.domain.result.login

sealed class LoginResult {
    data class SuccessLogin(val success: Boolean = true): LoginResult()
    data class ErrorLogin(val message: String): LoginResult()
}