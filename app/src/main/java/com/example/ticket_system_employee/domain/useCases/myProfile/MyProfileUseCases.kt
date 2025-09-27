package com.example.ticket_system_employee.domain.useCases.myProfile

import android.content.Context
import com.example.ticket_system_employee.domain.repository.myProfile.MyProfileRepository
import com.example.ticket_system_employee.domain.result.myProfile.MyProfileResult

class MyProfileUseCases(private val myProfileRepository: MyProfileRepository) {
    fun getContext(): Context = myProfileRepository.getContext()
    fun getEmployeeWithCompany(): MyProfileResult = myProfileRepository.getEmployeeWithCompany()
}