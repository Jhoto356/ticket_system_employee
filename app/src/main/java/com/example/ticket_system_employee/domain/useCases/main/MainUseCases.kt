package com.example.ticket_system_employee.domain.useCases.main

import com.example.ticket_system_employee.domain.repository.main.MainRepository
import com.example.ticket_system_employee.domain.result.main.MainResult

class MainUseCases(private val mainRepository: MainRepository) {
    fun getTickets(): MainResult {
        return mainRepository.getTickets()
    }

}