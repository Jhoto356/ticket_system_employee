package com.example.ticket_system_employee.core.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.ticket_system_employee.core.db.ConstantsDB

@Entity(tableName = ConstantsDB.COMPANY_TABLE, indices = [Index(value = ["nit"], unique = true)])
data class CompanyEntity(
    @PrimaryKey(true)
    @ColumnInfo("id")
    val id: Long = 0,
    @ColumnInfo("companyName")
    val companyName: String,
    @ColumnInfo("nit")
    val nit: String,
    @ColumnInfo("status")
    val status: Boolean

)
