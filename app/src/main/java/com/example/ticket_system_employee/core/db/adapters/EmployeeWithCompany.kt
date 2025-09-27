package com.example.ticket_system_employee.core.db.adapters

data class EmployeeWithCompany(
    val document: String = "",
    val email: String = "",
    val fullName: String = "",
    val fullLastName: String =  "",
    val companyName: String = "",
    val nit: String = ""
)
