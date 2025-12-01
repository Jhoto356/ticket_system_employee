package com.example.ticket_system_employee.core.db.adapters

import androidx.room.ColumnInfo
import com.example.ticket_system_employee.core.db.entities.AreaEntity
import com.example.ticket_system_employee.core.db.entities.TicketEntity

data class TicketAdapterToUI(
    val id: Long,
    val category: String,
    val areaId: String,
    val description: String,
    val status: Long,
    val registerDate: String,
)
