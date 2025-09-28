package com.example.ticket_system_employee.data.repositoryImp.myProfile

import android.content.Context
import com.example.ticket_system_employee.R
import com.example.ticket_system_employee.core.db.adapters.EmployeeWithCompany
import com.example.ticket_system_employee.dataSources.local.CompanyDataSource
import com.example.ticket_system_employee.dataSources.local.EmployeeDataSource
import com.example.ticket_system_employee.domain.repository.myProfile.MyProfileRepository
import com.example.ticket_system_employee.domain.result.myProfile.MyProfileResult
import com.example.ticket_system_employee.presentation.myProfile.MyProfileUISate
import org.koin.java.KoinJavaComponent.inject

class MyProfileRepositoryImp(private val context: Context): MyProfileRepository {
    /** DATA SOURCES **/
    private val employeeDataSource: EmployeeDataSource by inject(EmployeeDataSource::class.java)
    private val companyDataSource: CompanyDataSource by inject(CompanyDataSource::class.java)

    /** METHODS **/
    override fun getContext(): Context {
        return context
    }

    override fun getEmployeeWithCompany(): MyProfileResult {
        return try {
            val employee = employeeDataSource.getEmployeeInUse()
            if (employee == null) {
                val message = context.getString(R.string.txt_error_get_employee_with_company)
                return MyProfileResult.GetEmployeeWithCompanyError(message)
            }
            val company = companyDataSource.getCompanyById(employee.company)
            if (company == null) {
                val message = context.getString(R.string.txt_error_get_employee_with_company)
                return MyProfileResult.GetEmployeeWithCompanyError(message)
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
            MyProfileResult.GetEmployeeWithCompanySuccess(employeeWithCompany)
        } catch (e: Exception) {
            e.printStackTrace()
            val message = context.getString(R.string.txt_error_get_employee_with_company)
            MyProfileResult.GetEmployeeWithCompanyError(message)
        }

    }

    override fun validateChangePassword(myProfileUIState: MyProfileUISate): MyProfileResult {
        return try {
            val currentPassword = myProfileUIState.currentPasswordValue
            val newPassword = myProfileUIState.newPasswordValue
            val confirmPassword = myProfileUIState.confirmPasswordValue

            val employee = employeeDataSource.getEmployeeInUse()
            if (employee == null) {
                val message = context.getString(R.string.txt_error_change_password)
                return MyProfileResult.ChangePasswordError(message)
            }
            if (employee.password != currentPassword) {
                val message = context.getString(R.string.txt_error_current_password_wrong)
                return MyProfileResult.ChangePasswordError(message)
            }
            if (currentPassword == newPassword) {
                val message = context.getString(R.string.txt_error_current_and_new_password_equals)
                return MyProfileResult.ChangePasswordError(message)
            }
            if (newPassword != confirmPassword) {
                val message = context.getString(R.string.txt_error_new_and_confirm_password_wrong)
                return MyProfileResult.ChangePasswordError(message)
            }
            employeeDataSource.updatePassword(newPassword, employee.id)
            return MyProfileResult.ChangePasswordSuccess()
        } catch (e: Exception) {
            e.printStackTrace()
            val message = context.getString(R.string.txt_error_change_password)
            MyProfileResult.ChangePasswordError(message)
        }

    }
}