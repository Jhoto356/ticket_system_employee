package com.example.ticket_system_employee.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ticket_system_employee.core.db.daos.EmployeeDao
import com.example.ticket_system_employee.core.db.entities.EmployeeEntity

@Database(
    entities = [
        EmployeeEntity::class
    ],
    version = ConstantsDB.DB_VERSION,
    exportSchema = true
)
abstract class AppDB: RoomDatabase() {
    abstract fun employee(): EmployeeDao

}