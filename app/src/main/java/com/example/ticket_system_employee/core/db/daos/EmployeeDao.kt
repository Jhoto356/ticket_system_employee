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

    @Query("SELECT * FROM Employee WHERE email =:email AND password =:password AND company =:company LIMIT 1")
    fun getEmployeeByCredential(email: String, password: String, company: Long): EmployeeEntity?

    @Query("SELECT * FROM Employee WHERE inUse = 1 LIMIT 1")
    fun getEmployeeInUse(): EmployeeEntity?

    @Query("UPDATE Employee SET inUse = 1 WHERE id =:id")
    fun updateEmployeeInUse(id: Long)

}