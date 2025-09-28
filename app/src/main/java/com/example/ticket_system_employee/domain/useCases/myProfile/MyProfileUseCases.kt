package com.example.ticket_system_employee.domain.useCases.myProfile

import android.content.Context
import com.example.ticket_system_employee.domain.repository.myProfile.MyProfileRepository
import com.example.ticket_system_employee.domain.result.myProfile.MyProfileResult
import com.example.ticket_system_employee.presentation.myProfile.MyProfileUISate

class MyProfileUseCases(private val myProfileRepository: MyProfileRepository) {
    fun getContext(): Context = myProfileRepository.getContext()
    fun getEmployeeWithCompany(): MyProfileResult = myProfileRepository.getEmployeeWithCompany()
    fun validateChangePassword(myProfileUIState: MyProfileUISate): MyProfileResult {
        return myProfileRepository.validateChangePassword(myProfileUIState)
    }
}