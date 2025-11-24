package com.example.ticket_system_employee.core.uitls

enum class TicketStatus(val statusId: Long) {
    PENDING(0L),
    APPROVED(1L),
    REJECTED(2L),
    REMOVED(3L)
}