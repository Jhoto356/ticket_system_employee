package com.example.ticket_system_employee.core.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ticket_system_employee.core.db.ConstantsDB
import com.example.ticket_system_employee.core.db.entities.RequestTypeEntity

@Dao
interface RequestTypeDao {
    @Query("SELECT * FROM ${ConstantsDB.REQUEST_TYPE_TABLE}")
    fun getAllRequestsType(): List<RequestTypeEntity>

    @Query("SELECT * FROM ${ConstantsDB.REQUEST_TYPE_TABLE} WHERE status = 1")
    fun getEnabledRequestsType(): List<RequestTypeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRequestsType(areas: List<RequestTypeEntity>)
}