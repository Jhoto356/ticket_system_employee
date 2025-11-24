package com.example.ticket_system_employee.core.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.ticket_system_employee.core.db.ConstantsDB
import com.example.ticket_system_employee.core.db.entities.AreaEntity

@Dao
interface AreaDao {
    @Query("SELECT * FROM ${ConstantsDB.AREA_TABLE}")
    fun getAllAreas(): List<AreaEntity>
}