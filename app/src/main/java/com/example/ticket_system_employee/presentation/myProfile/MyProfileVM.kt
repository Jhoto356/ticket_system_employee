package com.example.ticket_system_employee.presentation.myProfile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticket_system_employee.core.db.adapters.EmployeeWithCompany
import com.example.ticket_system_employee.domain.result.myProfile.MyProfileResult
import com.example.ticket_system_employee.domain.useCases.myProfile.MyProfileUseCases
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.ticket_system_employee.R

data class MyProfileUISate(
    val error: Boolean = false,
    val employeeWithCompany: EmployeeWithCompany = EmployeeWithCompany(),
    val message: String = ""
)

class MyProfileVM(
    private val myProfileUseCases: MyProfileUseCases,
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {
    /** UTILS **/
    private val context: Context get() = myProfileUseCases.getContext()

    /** STATES **/
    private val myProfileUIState = MutableStateFlow(MyProfileUISate())
    val myProfileUIStateValue = myProfileUIState.asStateFlow()

    /** LIFECYCLE **/
    init {
        getEmployeeWithCompany()
    }

    /** METHODS **/
    private fun getEmployeeWithCompany() {
        viewModelScope.launch(dispatcherIO) {
            when(val result = myProfileUseCases.getEmployeeWithCompany()) {
                is MyProfileResult.GetEmployeeWithCompanySuccess -> {
                    myProfileUIState.update { it.copy(employeeWithCompany = result.employeeWithCompany) }
                }
                is MyProfileResult.GetEmployeeWithCompanyError -> {
                    myProfileUIState.update { it.copy(error = true, message = result.message) }
                }
                else -> {
                    val message = context.getString(R.string.txt_error_get_employee_with_company)
                    myProfileUIState.update { it.copy(error = true, message = message) }
                }
            }
        }

    }

}