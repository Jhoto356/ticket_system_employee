package com.example.ticket_system_employee.domain.repository.main

import com.example.ticket_system_employee.domain.result.main.MainResult

interface MainRepository {
    fun getTickets(): MainResult
}