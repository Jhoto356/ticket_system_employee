package com.example.ticket_system_employee.core.di

import com.example.ticket_system_employee.core.db.AppDB
import com.example.ticket_system_employee.core.db.daos.EmployeeDao

object DaoFactory {
    fun getEmployeeDao(appDB: AppDB): EmployeeDao {
        return appDB.employee()
    }
}