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

    fun insertEmployees() {
        return try {
            val company = companyDataSource.getCompany() ?: return
            val companyId = company.id
            val lstEmployeesToSave = listOf(
                EmployeeEntity(
                    document = "111222333", email = "maria.garcia@example.com",
                    password = "securePass1*", name = "María", secondName = "Fernanda", lastName = "García",
                    secondLastName = "López", enabled = true, inUse = false, company = companyId,
                ),
                EmployeeEntity(
                    document = "444555666", email = "juan.martinez@example.com",
                    password = "password_123", name = "Juan", secondName = null, lastName = "Martínez",
                    secondLastName = null, enabled = true, inUse = false, company = companyId
                ),
                EmployeeEntity(
                    document = "777888999", email = "ana.rodriguez@example.com",
                    password = "myPass!234", name = "Ana", secondName = "Isabel", lastName = "Rodríguez",
                    secondLastName = null, enabled = true, inUse = false, company = companyId
                )
            )
            employeeDao.insertEmployees(lstEmployeesToSave)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}