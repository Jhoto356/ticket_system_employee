package com.example.ticket_system_employee.core.db.adapters

import com.example.ticket_system_employee.core.db.entities.CompanyEntity

data class CompanyAdapterToUI(
    val id: Long,
    val companyName: String,
    val nit: String,
    val status: Boolean
)

fun CompanyEntity.toModel() = CompanyAdapterToUI(
    id = id,
    companyName = companyName,
    nit = nit,
    status = status
)