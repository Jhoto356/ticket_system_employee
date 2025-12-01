package com.example.ticket_system_employee.core.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.ticket_system_employee.core.db.ConstantsDB

@Entity(tableName = ConstantsDB.REQUEST_TYPE_TABLE, indices = [Index(value = ["requestTypeId"], unique = true)])
data class RequestTypeEntity(
    @PrimaryKey(true)
    @ColumnInfo("id")
    val id: Long = 0,
    @ColumnInfo("requestTypeId")
    val requestTypeId: Long,
    @ColumnInfo("description")
    val description: String,
    @ColumnInfo("status")
    val status: Boolean
)
