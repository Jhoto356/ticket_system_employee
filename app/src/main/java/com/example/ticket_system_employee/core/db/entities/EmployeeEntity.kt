package com.example.ticket_system_employee.core.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.ticket_system_employee.core.db.ConstantsDB

@Entity(tableName = ConstantsDB.EMPLOYEE_TABLE, indices = [Index(value = ["document", "email"], unique = true)])
data class EmployeeEntity (
    @PrimaryKey(true)
    @ColumnInfo("id")
    val id: Long = 0,
    @ColumnInfo("document")
    val document: String,
    @ColumnInfo("email")
    val email: String,
    @ColumnInfo("password")
    val password: String,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("secondName")
    val secondName: String?,
    @ColumnInfo("lastName")
    val lastName: String,
    @ColumnInfo("secondLastName")
    val secondLastName: String?,
    @ColumnInfo("enabled")
    val enabled: Boolean
)