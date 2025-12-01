package com.example.ticket_system_employee.data.repositoryImp.newRequest

import android.content.Context
import com.example.ticket_system_employee.R
import com.example.ticket_system_employee.core.db.adapters.EmployeeWithCompany
import com.example.ticket_system_employee.core.db.adapters.LookupItemsToUI
import com.example.ticket_system_employee.core.db.entities.TicketEntity
import com.example.ticket_system_employee.dataSources.local.AreaDataSource
import com.example.ticket_system_employee.dataSources.local.CompanyDataSource
import com.example.ticket_system_employee.dataSources.local.EmployeeDataSource
import com.example.ticket_system_employee.dataSources.local.RequestTypeDataSource
import com.example.ticket_system_employee.dataSources.local.TicketDataSource
import com.example.ticket_system_employee.domain.repository.newRequest.NewRequestRepository
import com.example.ticket_system_employee.domain.result.newRequest.NewRequestResult
import org.koin.java.KoinJavaComponent.inject

class NewRequestRepositoryImp(private val context: Context): NewRequestRepository {
    /** DATA SOURCES **/
    private val employeeDataSource: EmployeeDataSource by inject(EmployeeDataSource::class.java)
    private val companyDataSource: CompanyDataSource by inject(CompanyDataSource::class.java)
    private val areaDataSource: AreaDataSource by inject(AreaDataSource::class.java)
    private val requestTypeDataSource: RequestTypeDataSource by inject(RequestTypeDataSource::class.java)
    private val ticketDataSource: TicketDataSource by inject(TicketDataSource::class.java)

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
            val requestType = requestTypeDataSource.getEnabledRequestType()
            val area = areaDataSource.getEnabledAreas()
            if (requestType.isEmpty() || area.isEmpty()) {
                val message = context.getString(R.string.txt_error_get_information_by_request)
                return NewRequestResult.GetInformationError(message)
            }
            val lookupItemsToUI = LookupItemsToUI(
                requestType = requestType.first(), area = area.first()
            )
            NewRequestResult.GetInformationSuccess(employeeWithCompany, lookupItemsToUI)
        } catch (e: Exception) {
            e.printStackTrace()
            val message = context.getString(R.string.txt_error_get_information_by_request)
            return NewRequestResult.GetInformationError(message)
        }

    }

    override fun saveNewTicket(ticketEntity: TicketEntity): NewRequestResult {
        try {
            val lstTickets = ArrayList<TicketEntity>()
            lstTickets.add(ticketEntity)
            ticketDataSource.insertDefaultTickets(lstTickets)
            return NewRequestResult.SuccessSave
        } catch (e: Exception) {
            e.printStackTrace()
            return NewRequestResult.ErrorSave(context.getString(R.string.txt_create_request_error))
        }
    }
}