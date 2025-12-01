package com.example.ticket_system_employee.core.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.ticket_system_employee.core.db.ConstantsDB

@Entity(tableName = ConstantsDB.TICKET_TABLE)
data class TicketEntity(
    @PrimaryKey(true)
    @ColumnInfo("id")
    val id: Long = 0,
    @ColumnInfo("categoryId")
    val categoryId: Long,
    @ColumnInfo("areaId")
    val areaId: Long,
    @ColumnInfo("description")
    val description: String,
    @ColumnInfo("status")
    val status: Long,
    @ColumnInfo("registerDate")
    val registerDate: String,
    @ColumnInfo("modifiedDate")
    val modifiedDate: String
)
