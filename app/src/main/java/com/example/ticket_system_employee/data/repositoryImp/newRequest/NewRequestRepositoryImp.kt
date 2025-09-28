package com.example.ticket_system_employee.data.repositoryImp.newRequest

import android.content.Context
import com.example.ticket_system_employee.R
import com.example.ticket_system_employee.core.db.adapters.EmployeeWithCompany
import com.example.ticket_system_employee.core.db.adapters.LookupItemsToUI
import com.example.ticket_system_employee.dataSources.local.CompanyDataSource
import com.example.ticket_system_employee.dataSources.local.EmployeeDataSource
import com.example.ticket_system_employee.domain.repository.newRequest.NewRequestRepository
import com.example.ticket_system_employee.domain.result.newRequest.NewRequestResult
import org.koin.java.KoinJavaComponent.inject

class NewRequestRepositoryImp(private val context: Context): NewRequestRepository {
    /** DATA SOURCES **/
    private val employeeDataSource: EmployeeDataSource by inject(EmployeeDataSource::class.java)
    private val companyDataSource: CompanyDataSource by inject(CompanyDataSource::class.java)

    /** METHODS **/
    override fun getContext(): Context {
        return context
    }

    override fun getEmployeeWithCompany(): NewRequestResult {
        return try {
            val employee = employeeDataSource.getEmployeeInUse()
            if (employee == null) {
                val message = context.getString(R.string.txt_error_get_information_by_request)
                return NewRequestResult.GetInformationError(message)
            }
            val company = companyDataSource.getCompanyById(employee.company)
            if (company == null) {
                val message = context.getString(R.string.txt_error_get_information_by_request)
                return NewRequestResult.GetInformationError(message)
            }
            val document = context.getString(R.string.txt_document_information, employee.document)
            val fullName = context.getString(
                R.string.txt_full_name_or_last_name, employee.name, employee.secondName
            )
            val fullLastName = context.getString(
                R.string.txt_full_name_or_last_name, employee.lastName, employee.secondLastName
            )
            val employeeWithCompany = EmployeeWithCompany(
                document = document,
                email = employee.email,
                fullName = fullName,
                fullLastName = fullLastName,
                companyName = company.companyName,
                nit = company.nit
            )
            val lookupItemsToUI = LookupItemsToUI(
                requestType = "Equipos", area = "Operaciones"
            )
            NewRequestResult.GetInformationSuccess(employeeWithCompany, lookupItemsToUI)
        } catch (e: Exception) {
            e.printStackTrace()
            val message = context.getString(R.string.txt_error_get_information_by_request)
            return NewRequestResult.GetInformationError(message)
        }

    }
}