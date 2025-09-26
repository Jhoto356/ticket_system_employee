package com.example.ticket_system_employee.core.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ticket_system_employee.core.db.entities.EmployeeEntity

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployees(employees: List<EmployeeEntity>)

    @Query("SELECT * FROM Employee")
    fun getAllEmployees(): List<EmployeeEntity>

    @Query("SELECT * FROM Employee WHERE email =:email AND password =:password AND company =:company")
    fun getEmployeeByCredential(email: String, password: String, company: Long): EmployeeEntity?

}