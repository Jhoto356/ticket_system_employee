package com.example.ticket_system_employee.domain.useCases.newRequest

import android.content.Context
import com.example.ticket_system_employee.core.db.entities.TicketEntity
import com.example.ticket_system_employee.domain.repository.newRequest.NewRequestRepository
import com.example.ticket_system_employee.domain.result.newRequest.NewRequestResult

class NewRequestUseCases(private val newRequestRepository: NewRequestRepository) {
    fun getContext(): Context = newRequestRepository.getContext()
    fun getEmployeeWithCompany(): NewRequestResult = newRequestRepository.getEmployeeWithCompany()
    fun saveNewTicket(ticketEntity: TicketEntity) = newRequestRepository.saveNewTicket(ticketEntity)
}