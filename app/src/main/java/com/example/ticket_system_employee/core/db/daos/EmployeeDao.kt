package com.example.ticket_system_employee.core.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ticket_system_employee.core.db.ConstantsDB
import com.example.ticket_system_employee.core.db.entities.EmployeeEntity

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployees(employees: List<EmployeeEntity>)

    @Query("SELECT * FROM ${ConstantsDB.EMPLOYEE_TABLE}")
    fun getAllEmployees(): List<EmployeeEntity>

    @Query("SELECT * FROM ${ConstantsDB.EMPLOYEE_TABLE} WHERE email =:email AND password =:password AND company =:company LIMIT 1")
    fun getEmployeeByCredential(email: String, password: String, company: Long): EmployeeEntity?

    @Query("SELECT * FROM ${ConstantsDB.EMPLOYEE_TABLE} WHERE inUse = 1 LIMIT 1")
    fun getEmployeeInUse(): EmployeeEntity?

    @Query("UPDATE ${ConstantsDB.EMPLOYEE_TABLE} SET inUse = 1 WHERE id =:id")
    fun updateEmployeeInUse(id: Long)

    @Query("UPDATE ${ConstantsDB.EMPLOYEE_TABLE} SET password =:newPassword WHERE id =:id AND inUse = 1")
    fun updateEmployeePassword(newPassword: String, id: Long)

}