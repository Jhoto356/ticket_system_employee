package com.example.ticket_system_employee.dataSources.local

import com.example.ticket_system_employee.core.db.adapters.TicketAdapterToUI
import com.example.ticket_system_employee.core.db.adapters.toListModel
import com.example.ticket_system_employee.core.db.daos.TicketDao
import com.example.ticket_system_employee.core.db.entities.TicketEntity

class TicketDataSource(private val ticketDao: TicketDao) {
    fun getAllTickets(): List<TicketAdapterToUI> {
        val tickets = ticketDao.getAllTickets()
        if (tickets.isEmpty()) return emptyList()
        return tickets.toListModel()
    }
    fun insertDefaultTickets(lstTickets: List<TicketEntity>) {
        try {
            ticketDao.insertDefaultTickets(lstTickets)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}