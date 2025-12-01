package com.example.ticket_system_employee.core.db.adapters

data class TicketAdapterToUI(
    val id: Long,
    val category: String,
    val areaId: String,
    val description: String,
    val status: Long,
    val registerDate: String,
)

