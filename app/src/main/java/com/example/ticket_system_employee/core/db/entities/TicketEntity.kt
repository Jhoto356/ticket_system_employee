package com.example.ticket_system_employee.core.db.entities

data class TicketEntity(
    val requestType: String,
    val area: String,
    val description: String,
    val status: Int
)
