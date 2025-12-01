package com.example.ticket_system_employee.dataSources.local

import com.example.ticket_system_employee.core.db.adapters.EmployeeAdapterToUI
import com.example.ticket_system_employee.core.db.adapters.toListModel
import com.example.ticket_system_employee.core.db.adapters.toModel
import com.example.ticket_system_employee.core.db.daos.EmployeeDao
import com.example.ticket_system_employee.core.db.entities.EmployeeEntity
import org.koin.java.KoinJavaComponent.inject

class EmployeeDataSource(private val employeeDao: EmployeeDao) {
    private val companyDataSource: CompanyDataSource by inject(CompanyDataSource::class.java)

    fun updatePassword(newPassword: String, id: Long) {
        employeeDao.updateEmployeePassword(newPassword, id)
    }

    fun getEmployeeByCredentials(email: String, password: String, company: Long): EmployeeAdapterToUI? {
        val employee = employeeDao.getEmployeeByCredential(email, password, company)
        return employee?.toModel()
    }

    fun getEmployeeInUse(): EmployeeAdapterToUI? {
        val employee = employeeDao.getEmployeeInUse()
        return employee?.toModel()

    }

    fun updateEmployeeInUse(id: Long) {
        employeeDao.updateEmployeeInUse(id)
    }

    fun getEmployees(): List<EmployeeAdapterToUI> {
        val listOfEntity = employeeDao.getAllEmployees()
        if (listOfEntity.isEmpty()) {
            return emptyList()
        }
        val lstOfModel = listOfEntity.toListModel()
        return lstOfModel

    }

    fun insertEmployees(lstEmployeesToSave: List<EmployeeEntity>) {
        return try {
            employeeDao.insertEmployees(lstEmployeesToSave)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}