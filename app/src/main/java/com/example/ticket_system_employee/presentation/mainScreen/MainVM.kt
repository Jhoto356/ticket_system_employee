package com.example.ticket_system_employee.presentation.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticket_system_employee.core.db.adapters.TicketAdapterToUI
import com.example.ticket_system_employee.domain.result.main.MainResult
import com.example.ticket_system_employee.domain.useCases.main.MainUseCases
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MainUIState(
    val lstTickets: ArrayList<TicketAdapterToUI> = ArrayList(),
    val failureMessage: String = "",
    val notContent: Boolean = false,
    val error: Boolean = false
)

class MainVM(
    private val mainUseCases: MainUseCases,
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {
    /** STATES **/
    private val mainUIState = MutableStateFlow(MainUIState())
    val mainUIStateValues = mainUIState.asStateFlow()

    private fun setLstTickets(lstTickets: ArrayList<TicketAdapterToUI>) {
        mainUIState.update { it.copy(lstTickets = lstTickets) }
    }
    fun setFailure(error: Boolean, message: String = "") {
        mainUIState.update { it.copy(error = error, failureMessage = message) }
    }
    fun setNotContent(notContent: Boolean) {
        mainUIState.update { it.copy(notContent = notContent) }
    }

    /** LIFECYCLE **/
    init {
        getTickets()
    }

    /** METHODS **/
    fun getTickets() {
        viewModelScope.launch(dispatcherIO) {
            when(val result = mainUseCases.getTickets()) {
                is MainResult.Tickets -> {
                    setLstTickets(result.tickets)
                }
                is MainResult.Failure -> {
                    setFailure(true, result.message)
                }
                is MainResult.NotContent -> {
                    setNotContent(true)
                }
            }
        }

    }
}