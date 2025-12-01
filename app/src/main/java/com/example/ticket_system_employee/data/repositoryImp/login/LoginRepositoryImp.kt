package com.example.ticket_system_employee.data.repositoryImp.login

import android.content.Context
import android.util.Log
import com.example.ticket_system_employee.R
import com.example.ticket_system_employee.core.db.entities.AreaEntity
import com.example.ticket_system_employee.core.db.entities.CompanyEntity
import com.example.ticket_system_employee.core.db.entities.EmployeeEntity
import com.example.ticket_system_employee.core.db.entities.RequestTypeEntity
import com.example.ticket_system_employee.core.db.entities.TicketEntity
import com.example.ticket_system_employee.core.uitls.TicketStatus
import com.example.ticket_system_employee.dataSources.local.AreaDataSource
import com.example.ticket_system_employee.dataSources.local.CompanyDataSource
import com.example.ticket_system_employee.dataSources.local.EmployeeDataSource
import com.example.ticket_system_employee.dataSources.local.RequestTypeDataSource
import com.example.ticket_system_employee.dataSources.local.TicketDataSource
import com.example.ticket_system_employee.domain.repository.login.LoginRepository
import com.example.ticket_system_employee.domain.result.login.LoginResult
import com.example.ticket_system_employee.presentation.commons.models.EmployeeToLogin
import org.koin.java.KoinJavaComponent

class LoginRepositoryImp(private val context: Context): LoginRepository {
    /** DATA SOURCES **/
    private val companyDataSource: CompanyDataSource by KoinJavaComponent.inject(CompanyDataSource::class.java)
    private val employeeDataSource: EmployeeDataSource by KoinJavaComponent.inject(EmployeeDataSource::class.java)
    private val ticketDataSource: TicketDataSource by KoinJavaComponent.inject(TicketDataSource::class.java)
    private val requestTypeDataSource: RequestTypeDataSource by KoinJavaComponent.inject(RequestTypeDataSource::class.java)
    private val areaDataSource: AreaDataSource by KoinJavaComponent.inject(AreaDataSource::class.java)

    /** METHODS **/
    override fun getContext(): Context { return context }

    override fun validateLogion(employee: EmployeeToLogin): LoginResult {
        return try {
            val company = companyDataSource.getCompanyByName(employee.companyName)
            if (company == null) {
                val message = context.getString(R.string.txt_error_verify_information, employee.companyName)
                Log.i("${javaClass.simpleName}", message)
                return LoginResult.ErrorLogin(message)
            }
            val employee = employeeDataSource.getEmployeeByCredentials(
                employee.email, employee.password, company.id
            )
            if (employee == null) {
                val message = context.getString(R.string.txt_error_verify_information, company.companyName)
                return LoginResult.ErrorLogin(message)
            }
            val employeeInUse = employeeDataSource.getEmployeeInUse()
            if (employeeInUse == null) {
                employeeDataSource.updateEmployeeInUse(employee.id)
                return LoginResult.SuccessLogin()
            }
            if (employeeInUse.id != employee.id) {
                val message = context.getString(R.string.txt_error_verify_information, company.companyName)
                LoginResult.ErrorLogin(message)
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
            validateAreasData()
            validateRequestsTypeData()
            validateTicketsData()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun validateAreasData() {
        val areas = areaDataSource.getAllAreas()
        if (areas.isNotEmpty()) return
        val lstAreas = ArrayList<AreaEntity>()
        val operation = AreaEntity(
            areaId = 1,
            description = context.getString(R.string.txt_operation_area),
            status = true
        )
        lstAreas.add(operation)
        areaDataSource.insertAreas(lstAreas)
    }

    private fun validateRequestsTypeData() {
        val requestsType = requestTypeDataSource.getAllRequestType()
        if (requestsType.isNotEmpty()) return
        val lstRequestType = ArrayList<RequestTypeEntity>()
        val requestType = RequestTypeEntity(
            requestTypeId = 1,
            description = context.getString(R.string.txt_request_type_equipment),
            status = true
        )
        lstRequestType.add(requestType)
        requestTypeDataSource.insertRequestType(lstRequestType)
    }

    private fun validateTicketsData() {
        val tickets = ticketDataSource.getAllTickets()
        if (tickets.isNotEmpty()) return
        val area = areaDataSource.getEnabledAreas().first()
        val requestType = requestTypeDataSource.getEnabledRequestType().first()
        val lstTickets = listOf(
            TicketEntity(
                categoryId = requestType.id, areaId = area.id,
                description = context.getString(R.string.txt_ticket_description_mac_24_512_gb),
                status = TicketStatus.REMOVED.statusId, registerDate = "2025-06-17 12:35:10",
                modifiedDate = "2025-06-19 10:30:15"
            ),
            TicketEntity(
                categoryId = requestType.id, areaId = area.id,
                description = context.getString(R.string.txt_ticket_description_lenovo_think_pad_16_512_gb),
                status = TicketStatus.APPROVED.statusId, registerDate = "2025-06-13 02:55:35",
                modifiedDate = "2025-06-15 11:37:28"
            ),
            TicketEntity(
                categoryId = requestType.id, areaId = area.id,
                description = context.getString(R.string.txt_ticket_description_iphone_17_pro_max),
                status = TicketStatus.REMOVED.statusId, registerDate = "2025-06-17 01:47:10",
                modifiedDate = "2025-06-19 10:30:15"
            )
        )
        ticketDataSource.insertDefaultTickets(lstTickets)
    }

    private fun validateCompanyData() {
        val company = companyDataSource.getCompany()
        if (company != null) return
        val companyToSave = CompanyEntity(
            companyName = "Areandina",
            nit = "09-123456789",
            status = true
        )
        companyDataSource.insertCompany(companyToSave)
    }

    private fun validateEmployeeData() {
        val employees = employeeDataSource.getEmployees()
        if (employees.isNotEmpty()) return
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
        employeeDataSource.insertEmployees(lstEmployeesToSave)
    }


}