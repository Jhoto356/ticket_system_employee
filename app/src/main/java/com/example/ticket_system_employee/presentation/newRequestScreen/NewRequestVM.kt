package com.example.ticket_system_employee.presentation.newRequestScreen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticket_system_employee.domain.useCases.newRequest.NewRequestUseCases
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.ticket_system_employee.R
import com.example.ticket_system_employee.core.db.adapters.EmployeeWithCompany
import com.example.ticket_system_employee.core.db.adapters.LookupItemsToUI
import com.example.ticket_system_employee.domain.result.newRequest.NewRequestResult

data class NewRequestUIState(
    val requestTypeFocus: Boolean = false,
    val requestTypeValue: String = "",
    val areaFocus: Boolean = false,
    val areaValue: String = "",
    val descriptionValue: String = "",
    val isLoading: Boolean = false,
    val loadingMessage: String = "",
    val employeeWithCompany: EmployeeWithCompany = EmployeeWithCompany(),
    val lookupItemsToUI: LookupItemsToUI = LookupItemsToUI(),
    val errorStatus: Boolean = false,
    val errorMessage: String = "",
    val enabledButton: Boolean = false
)

class NewRequestVM(
    private val newRequestUseCases: NewRequestUseCases,
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {
    /** UTILS **/
    private val context: Context get() = newRequestUseCases.getContext()

    /** STATES **/
    private val newRequestUIState = MutableStateFlow(NewRequestUIState())
    val newRequestUIStateValue = newRequestUIState.asStateFlow()

    fun setRequestTypeFocus(value: Boolean) {
        newRequestUIState.update { it.copy(requestTypeFocus = value) }
    }
    fun setRequestTypeValue(value: String) {
        newRequestUIState.update { it.copy(requestTypeValue = value) }
        setEnabledButton()
    }
    fun setAreaFocus(value: Boolean) {
        newRequestUIState.update { it.copy(areaFocus = value) }
    }
    fun setAreaValue(value: String) {
        newRequestUIState.update { it.copy(areaValue = value) }
        setEnabledButton()
    }
    fun setDescriptionValue(value: String) {
        newRequestUIState.update { it.copy(descriptionValue = value) }
        setEnabledButton()
    }
    fun setIsLoading(value: Boolean, message: String = "") {
        newRequestUIState.update { it.copy(isLoading = value, loadingMessage = message) }
    }
    fun setErrorStatus(value: Boolean, message: String = "") {
        newRequestUIState.update { it.copy(errorStatus = value, errorMessage = message) }
    }
    fun setEnabledButton() {
        val requestType = newRequestUIState.value.requestTypeValue.isNotEmpty()
        val area = newRequestUIState.value.areaValue.isNotEmpty()
        val description =newRequestUIState.value.descriptionValue.isNotEmpty()
        val enabled = requestType && area && description
        newRequestUIState.update { it.copy(enabledButton = enabled) }
    }

    /** LIFECYCLE **/
    init {
        setIsLoading(true, context.getString(R.string.txt_getting_information_employee_and_company))
        getEmployeeWithCompany()
    }

    /** METHODS **/
    private fun getEmployeeWithCompany() {
        viewModelScope.launch(dispatcherIO) {
            when(val result = newRequestUseCases.getEmployeeWithCompany()) {
                is NewRequestResult.GetInformationSuccess -> {
                    newRequestUIState.update {
                        it.copy(employeeWithCompany = result.employeeWithCompany, lookupItemsToUI = result.lookupsItem)
                    }
                }
                is NewRequestResult.GetInformationError -> {
                    setErrorStatus(true, result.message)
                }
                else -> {
                    val message = context.getString(R.string.txt_error_get_information_by_request)
                    setErrorStatus(true, message)
                }
            }

        }
    }

}