package com.example.ticket_system_employee.core.db.adapters

import com.example.ticket_system_employee.core.db.entities.EmployeeEntity

data class EmployeeAdapterToUI(
    val id: Long,
    val document: String,
    val email: String,
    val password: String,
    val name: String,
    val secondName: String?,
    val lastName: String,
    val secondLastName: String?,
    val enabled: Boolean,
    val inUse: Boolean
)

fun EmployeeEntity.toModel() = EmployeeAdapterToUI(
    id = id,
    document = document,
    email = email,
    password = password,
    name = name,
    secondName = secondName,
    lastName = lastName,
    secondLastName = secondLastName,
    enabled = enabled,
    inUse = inUse

)

fun List<EmployeeEntity>.toListModel(): List<EmployeeAdapterToUI> {
    return this.map { it.toModel() }
}