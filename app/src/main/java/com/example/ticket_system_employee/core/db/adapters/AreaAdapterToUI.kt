package com.example.ticket_system_employee.core.db.adapters

import androidx.room.ColumnInfo
import com.example.ticket_system_employee.core.db.entities.AreaEntity
import com.example.ticket_system_employee.core.db.entities.EmployeeEntity

data class AreaAdapterToUI(
    val id: Long,
    val description: String,
    val status: Boolean
)

fun AreaEntity.toModel() = AreaAdapterToUI(
    id = areaId,
    description = description,
    status = status
)

fun List<AreaEntity>.toListModel(): List<AreaAdapterToUI> {
    return this.map { it.toModel() }
}
