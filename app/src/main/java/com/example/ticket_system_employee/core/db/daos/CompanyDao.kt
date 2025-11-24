package com.example.ticket_system_employee.core.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ticket_system_employee.core.db.ConstantsDB
import com.example.ticket_system_employee.core.db.entities.CompanyEntity

@Dao
interface CompanyDao {
    @Query("SELECT * FROM ${ConstantsDB.COMPANY_TABLE}")
    fun getCompany(): CompanyEntity?

    @Query("SELECT * FROM ${ConstantsDB.COMPANY_TABLE} WHERE companyName =:companyName")
    fun getCompanyByName(companyName: String): CompanyEntity?

    @Query("SELECT * FROM ${ConstantsDB.COMPANY_TABLE} WHERE id =:id")
    fun getCompanyById(id: Long): CompanyEntity?

    @Insert
    fun insertCompany(company: CompanyEntity)

}