package com.example.ticket_system_employee.domain.repository.newRequest

import android.content.Context
import com.example.ticket_system_employee.domain.result.newRequest.NewRequestResult

interface NewRequestRepository {
    fun getContext(): Context
    fun getEmployeeWithCompany(): NewRequestResult
}