package com.example.ticket_system_employee.core.uitls

import com.example.ticket_system_employee.core.db.entities.TicketEntity

object UtilsProject {
    val lstTickets = arrayListOf(
        TicketEntity(
            requestType = "Equipo",
            area = "Operaciones",
            description = "iPhone 15",
            status = 0 // Rejected
        ),
        TicketEntity(
            requestType = "Equipo",
            area = "Operaciones",
            description = "MacBook Pro M4 512/24 gb",
            status = 3 // Removed
        ),
        TicketEntity(
            requestType = "Equipo",
            area = "Operaciones",
            description = "Thinkpad 256/16 gb",
            status = 1 // Aprobado
        )
    )
}