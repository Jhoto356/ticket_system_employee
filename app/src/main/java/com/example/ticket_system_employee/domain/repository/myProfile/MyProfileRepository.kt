package com.example.ticket_system_employee.domain.repository.myProfile

import android.content.Context
import com.example.ticket_system_employee.domain.result.myProfile.MyProfileResult

interface MyProfileRepository {
    fun getContext(): Context
    fun getEmployeeWithCompany(): MyProfileResult
}