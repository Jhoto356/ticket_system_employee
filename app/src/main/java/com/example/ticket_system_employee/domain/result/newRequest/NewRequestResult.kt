package com.example.ticket_system_employee.domain.result.newRequest

import com.example.ticket_system_employee.core.db.adapters.EmployeeWithCompany
import com.example.ticket_system_employee.core.db.adapters.LookupItemsToUI

sealed class NewRequestResult {
    data class GetInformationError(val message: String): NewRequestResult()
    data class GetInformationSuccess(val employeeWithCompany: EmployeeWithCompany, val lookupsItem: LookupItemsToUI): NewRequestResult()
    data object SuccessSave: NewRequestResult()
    data class ErrorSave(val message: String): NewRequestResult()
}