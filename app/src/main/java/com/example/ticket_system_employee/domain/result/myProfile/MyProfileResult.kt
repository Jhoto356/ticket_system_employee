package com.example.ticket_system_employee.domain.result.myProfile

import com.example.ticket_system_employee.core.db.adapters.EmployeeWithCompany

sealed class MyProfileResult {
    data class GetEmployeeWithCompanyError(val message: String): MyProfileResult()
    data class GetEmployeeWithCompanySuccess(val employeeWithCompany: EmployeeWithCompany): MyProfileResult()
    data class ChangePasswordError(val message: String): MyProfileResult()
    data class ChangePasswordSuccess(val success: Boolean = true): MyProfileResult()
}