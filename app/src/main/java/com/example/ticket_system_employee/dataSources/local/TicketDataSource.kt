package com.example.ticket_system_employee.dataSources.local

import com.example.ticket_system_employee.core.db.daos.TicketDao
import com.example.ticket_system_employee.core.db.entities.TicketEntity

class TicketDataSource(private val ticketDao: TicketDao) {
    fun getAllTickets(): List<TicketEntity> {
        val tickets = ticketDao.getAllTickets()
        if (tickets.isEmpty()) return emptyList()
        return tickets
    }
    fun insertDefaultTickets(lstTickets: List<TicketEntity>) {
        try {
            ticketDao.insertDefaultTickets(lstTickets)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}