package com.example.ticket_system_employee.core.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ticket_system_employee.core.db.ConstantsDB
import com.example.ticket_system_employee.core.db.entities.TicketEntity

@Dao
interface TicketDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDefaultTickets(tickets: List<TicketEntity>)
    @Query("SELECT * FROM ${ConstantsDB.TICKET_TABLE}")
    fun getAllTickets(): List<TicketEntity>


}