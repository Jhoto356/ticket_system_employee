package com.example.ticket_system_employee.core.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ticket_system_employee.core.db.ConstantsDB

@Entity(tableName = ConstantsDB.REQUEST_TYPE_TABLE)
data class RequestTypeEntity(
    @PrimaryKey(true)
    @ColumnInfo("id")
    val id: Long = 0,
    @ColumnInfo("description")
    val description: String,
    @ColumnInfo("status")
    val status: Boolean
)
