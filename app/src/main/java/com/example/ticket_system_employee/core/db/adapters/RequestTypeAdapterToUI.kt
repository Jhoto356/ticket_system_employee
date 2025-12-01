package com.example.ticket_system_employee.core.db.adapters

import com.example.ticket_system_employee.core.db.entities.RequestTypeEntity

data class RequestTypeAdapterToUI(
    val id: Long,
    val description: String,
    val status: Boolean
)

fun RequestTypeEntity.toModel() = RequestTypeAdapterToUI (
    id = requestTypeId,
    description = description,
    status = status
)

fun List<RequestTypeEntity>.toListModel(): List<RequestTypeAdapterToUI> {
    return this.map { it.toModel() }
}

