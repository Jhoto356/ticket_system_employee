package com.example.ticket_system_employee.domain.result.main

import com.example.ticket_system_employee.core.db.adapters.TicketAdapterToUI

sealed class MainResult {
    data object NotContent: MainResult()
    data class Failure(val message: String): MainResult()
    data class Tickets(val tickets: ArrayList<TicketAdapterToUI>): MainResult()
}