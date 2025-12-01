package com.example.ticket_system_employee.core.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.ticket_system_employee.core.db.ConstantsDB

@Entity(tableName = ConstantsDB.AREA_TABLE, indices = [Index(value = ["areaId"], unique = true)])
data class AreaEntity(
    @PrimaryKey(true)
    @ColumnInfo("id")
    val id: Long = 0,
    @ColumnInfo("areaId")
    val areaId: Long,
    @ColumnInfo("description")
    val description: String,
    @ColumnInfo("status")
    val status: Boolean
)
