package com.example.ticket_system_employee.core.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ticket_system_employee.core.db.entities.CompanyEntity

@Dao
interface CompanyDao {
    @Query("SELECT * FROM Company")
    fun getCompany(): CompanyEntity?

    @Query("SELECT * FROM Company WHERE companyName =:companyName")
    fun getCompanyByName(companyName: String): CompanyEntity?

    @Insert
    fun insertCompany(company: CompanyEntity)

}