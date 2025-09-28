package com.example.ticket_system_employee.domain.repository.myProfile

import android.content.Context
import com.example.ticket_system_employee.domain.result.myProfile.MyProfileResult
import com.example.ticket_system_employee.presentation.myProfile.MyProfileUISate

interface MyProfileRepository {
    fun getContext(): Context
    fun getEmployeeWithCompany(): MyProfileResult
    fun validateChangePassword(myProfileUIState: MyProfileUISate): MyProfileResult
}