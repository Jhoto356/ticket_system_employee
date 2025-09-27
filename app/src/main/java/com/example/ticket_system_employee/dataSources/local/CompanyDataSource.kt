package com.example.ticket_system_employee.dataSources.local

import com.example.ticket_system_employee.core.db.adapters.CompanyAdapterToUI
import com.example.ticket_system_employee.core.db.adapters.toModel
import com.example.ticket_system_employee.core.db.daos.CompanyDao
import com.example.ticket_system_employee.core.db.entities.CompanyEntity

class CompanyDataSource(private val companyDao: CompanyDao) {
    fun getCompanyByName(companyName: String): CompanyAdapterToUI? {
        val company = companyDao.getCompanyByName(companyName)
        return company?.toModel()

    }

    fun getCompanyById(id: Long): CompanyAdapterToUI? {
        val company = companyDao.getCompanyById(id)
        return company?.toModel()

    }

    fun getCompany(): CompanyAdapterToUI? {
        val companyEntity = companyDao.getCompany()
        return companyEntity?.toModel()

    }

    fun insertCompany() {
        return try {
            val companyToSave = CompanyEntity(
                companyName = "Areandina",
                nit = "09-123456789",
                status = true
            )
            companyDao.insertCompany(companyToSave)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}