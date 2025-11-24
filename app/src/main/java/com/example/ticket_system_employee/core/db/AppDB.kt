package com.example.ticket_system_employee.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ticket_system_employee.core.db.daos.AreaDao
import com.example.ticket_system_employee.core.db.daos.CompanyDao
import com.example.ticket_system_employee.core.db.daos.EmployeeDao
import com.example.ticket_system_employee.core.db.daos.RequestTypeDao
import com.example.ticket_system_employee.core.db.daos.TicketDao
import com.example.ticket_system_employee.core.db.entities.AreaEntity
import com.example.ticket_system_employee.core.db.entities.CompanyEntity
import com.example.ticket_system_employee.core.db.entities.EmployeeEntity
import com.example.ticket_system_employee.core.db.entities.RequestTypeEntity
import com.example.ticket_system_employee.core.db.entities.TicketEntity

@Database(
    entities = [
        EmployeeEntity::class,
        CompanyEntity::class,
        TicketEntity::class,
        RequestTypeEntity::class,
        AreaEntity::class
    ],
    version = ConstantsDB.DB_VERSION,
    exportSchema = true

)
abstract class AppDB: RoomDatabase() {
    abstract fun employee(): EmployeeDao
    abstract fun company(): CompanyDao
    abstract fun ticket(): TicketDao
    abstract fun requestType(): RequestTypeDao
    abstract fun area(): AreaDao
}