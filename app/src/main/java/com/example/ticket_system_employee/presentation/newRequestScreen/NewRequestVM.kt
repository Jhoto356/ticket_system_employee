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
import com.example.ticket_system_employee.core.db.adapters.AreaAdapterToUI
import com.example.ticket_system_employee.core.db.adapters.EmployeeWithCompany
import com.example.ticket_system_employee.core.db.adapters.LookupItemsToUI
import com.example.ticket_system_employee.core.db.adapters.RequestTypeAdapterToUI
import com.example.ticket_system_employee.core.db.entities.TicketEntity
import com.example.ticket_system_employee.core.uitls.DateAndTimeUtils
import com.example.ticket_system_employee.core.uitls.TicketStatus
import com.example.ticket_system_employee.domain.result.newRequest.NewRequestResult
import org.koin.java.KoinJavaComponent.inject

data class NewRequestUIState(
    val requestTypeFocus: Boolean = false,
    val requestTypeValue: RequestTypeAdapterToUI ?= null,
    val areaFocus: Boolean = false,
    val areaValue: AreaAdapterToUI ?= null,
    val descriptionValue: String = "",
    val isLoading: Boolean = false,
    val loadingMessage: String = "",
    val employeeWithCompany: EmployeeWithCompany = EmployeeWithCompany(),
    val lookupItemsToUI: LookupItemsToUI = LookupItemsToUI(),
    val errorStatus: Boolean = false,
    val errorMessage: String = "",
    val enabledButton: Boolean = false,
    val ticketSuccessSave: Boolean = false
)

class NewRequestVM(
    private val newRequestUseCases: NewRequestUseCases,
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {
    /** UTILS **/
    private val context: Context get() = newRequestUseCases.getContext()
    private val dateAndTimeUtils: DateAndTimeUtils by inject(DateAndTimeUtils::class.java)

    /** STATES **/
    private val newRequestUIState = MutableStateFlow(NewRequestUIState())
    val newRequestUIStateValue = newRequestUIState.asStateFlow()

    fun setRequestTypeFocus(value: Boolean) {
        newRequestUIState.update { it.copy(requestTypeFocus = value) }
    }
    fun setTicketSuccessSave(value: Boolean) {
        newRequestUIState.update { it.copy(ticketSuccessSave = value) }
    }
    fun setRequestTypeValue(value: RequestTypeAdapterToUI) {
        newRequestUIState.update { it.copy(requestTypeValue = value) }
        setEnabledButton()
    }
    fun setAreaFocus(value: Boolean) {
        newRequestUIState.update { it.copy(areaFocus = value) }
    }
    fun setAreaValue(value: AreaAdapterToUI) {
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
        val requestType = newRequestUIState.value.requestTypeValue?.id != 0L
        val area = newRequestUIState.value.areaValue?.id != 0L
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

    fun addNewTicket() {
        if (newRequestUIState.value.areaValue?.id == null || newRequestUIState.value.requestTypeValue?.id == null) {
            setErrorStatus(true, context.getString(R.string.txt_create_request_error))
            return
        }
        val ticket = TicketEntity(
            categoryId = newRequestUIState.value.requestTypeValue!!.id,
            areaId = newRequestUIState.value.areaValue!!.id, modifiedDate = "",
            description = newRequestUIState.value.descriptionValue,
            status = TicketStatus.PENDING.statusId, registerDate = dateAndTimeUtils.getDateAndTime()
        )
        viewModelScope.launch(dispatcherIO) {
            when(val result = newRequestUseCases.saveNewTicket(ticket)) {
                is NewRequestResult.SuccessSave -> {
                    setTicketSuccessSave(true)
                }
                is NewRequestResult.ErrorSave -> {
                    setErrorStatus(true, result.message)
                }
                else -> {
                    val message = context.getString(R.string.txt_create_request_error)
                    setErrorStatus(true, message)
                }
            }
        }

    }

}