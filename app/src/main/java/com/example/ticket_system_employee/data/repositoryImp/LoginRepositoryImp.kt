package com.example.ticket_system_employee.data.repositoryImp

import android.content.Context
import com.example.ticket_system_employee.domain.repository.login.LoginRepository
import com.example.ticket_system_employee.domain.result.login.LoginResult
import com.example.ticket_system_employee.presentation.commons.models.EmployeeToLogin
import com.example.ticket_system_employee.R
import com.example.ticket_system_employee.dataSources.local.CompanyDataSource
import com.example.ticket_system_employee.dataSources.local.EmployeeDataSource
import org.koin.java.KoinJavaComponent.inject

class LoginRepositoryImp(private val context: Context): LoginRepository {
    /** DATA SOURCES **/
    private val companyDataSource: CompanyDataSource by inject(CompanyDataSource::class.java)
    private val employeeDataSource: EmployeeDataSource by inject(EmployeeDataSource::class.java)

    /** METHODS **/
    override fun getContext(): Context { return context }

    override fun validateLogion(employee: EmployeeToLogin): LoginResult {
        return try {
            val company = companyDataSource.getCompanyByName(employee.companyName)
            if (company == null) {
                val message = context.getString(R.string.txt_not_linked_to_company)
                return LoginResult.ErrorLogin(message)
            }
            val employee = employeeDataSource.getEmployeeByCredentials(
                employee.email, employee.password, company.id
            )
            if (employee == null) {
                val message = context.getString(R.string.txt_not_linked_to_company)
                return LoginResult.ErrorLogin(message)
            }
            LoginResult.SuccessLogin()
        } catch (e: Exception) {
            e.printStackTrace()
            val message = context.getString(R.string.txt_error_login)
            LoginResult.ErrorLogin(message)
        }

    }

    override fun validateInitialData() {
        try {
            validateCompanyData()
            validateEmployeeData()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun validateCompanyData() {
        val company = companyDataSource.getCompany()
        if (company != null) return
        companyDataSource.insertCompany()
    }

    private fun validateEmployeeData() {
        val employees = employeeDataSource.getEmployees()
        if (employees.isNotEmpty()) return
        employeeDataSource.insertEmployees()
    }


}