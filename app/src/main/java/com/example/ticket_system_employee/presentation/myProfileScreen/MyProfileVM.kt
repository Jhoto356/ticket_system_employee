package com.example.ticket_system_employee.presentation.myProfileScreen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticket_system_employee.core.db.adapters.EmployeeWithCompany
import com.example.ticket_system_employee.domain.result.myProfile.MyProfileResult
import com.example.ticket_system_employee.domain.useCases.myProfile.MyProfileUseCases
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.ticket_system_employee.R

data class MyProfileUISate(
    val error: Boolean = false,
    val employeeWithCompany: EmployeeWithCompany = EmployeeWithCompany(),
    val message: String = "",
    val enabledButtonChangePassword: Boolean = false,
    val isLoading: Boolean = false,
    val loadingMessage: String = "",
    val showDialogChangePassword: Boolean = false,
    val currentPasswordValue: String = "",
    val newPasswordValue: String = "",
    val confirmPasswordValue: String = "",
    val enabledButtonConfirmPasswordChange: Boolean = false,
    val passwordFocus: Boolean = false,
    val currentPasswordVisible: Boolean = false,
    val newPasswordVisible: Boolean = false,
    val confirmPasswordVisible: Boolean = false,
    val currentPasswordFocus: Boolean = false,
    val newPasswordFocus: Boolean = false,
    val confirmPasswordFocus: Boolean = false,
    val changePasswordError: Boolean = false,
    val changePasswordSuccess: Boolean = false
)

class MyProfileVM(
    private val myProfileUseCases: MyProfileUseCases,
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {
    /** UTILS **/
    private val context: Context get() = myProfileUseCases.getContext()

    /** STATES **/
    private val myProfileUIState = MutableStateFlow(MyProfileUISate())
    val myProfileUIStateValue = myProfileUIState.asStateFlow()

    fun setChangePasswordError(value: Boolean, message: String = "") {
        myProfileUIState.update { it.copy(changePasswordError = value, message = message) }
    }
    fun setChangePasswordSuccess(value: Boolean) {
        myProfileUIState.update { it.copy(changePasswordSuccess = value) }
    }
    fun setCurrentPasswordValue(value: String) {
        myProfileUIState.update { it.copy(currentPasswordValue = value) }
        setEnabledButton()
    }
    fun setNewPasswordValue(value: String) {
        myProfileUIState.update { it.copy(newPasswordValue = value) }
        setEnabledButton()
    }

    fun setConfirmPasswordValue(value: String) {
        myProfileUIState.update { it.copy(confirmPasswordValue = value) }
        setEnabledButton()
    }
    fun setEnabledButton() {
        val currentPassword = myProfileUIState.value.currentPasswordValue.isNotEmpty()
        val newPassword = myProfileUIState.value.newPasswordValue.isNotEmpty()
        val confirmPassword = myProfileUIState.value.confirmPasswordValue.isNotEmpty()
        val enabled = confirmPassword && newPassword && currentPassword
        myProfileUIState.update { it.copy(enabledButtonConfirmPasswordChange = enabled) }
        myProfileUIState.update { it.copy(changePasswordError = false) }
    }
    fun setIsLoading(value: Boolean, message: String = "") {
        myProfileUIState.update { it.copy(isLoading = value, loadingMessage = message) }
    }
    fun setError(value: Boolean, message: String = "") {
        myProfileUIState.update { it.copy(error = value, message = message) }
    }
    fun setShowDialogChangePassword(value: Boolean) {
        myProfileUIState.update {
            it.copy(
                showDialogChangePassword = value, currentPasswordValue = "",
                newPasswordValue = "", confirmPasswordValue = ""
            )
        }
    }
    fun setCurrentPasswordVisible(isVisible: Boolean) {
        myProfileUIState.update {
            it.copy(currentPasswordVisible = !isVisible)
        }
    }
    fun setCurrentPasswordFocus(isFocus: Boolean, isVisible: Boolean) {
        if (!isFocus) {
            myProfileUIState.update {
                it.copy(currentPasswordFocus = false, currentPasswordVisible = false)
            }
            return
        }
        myProfileUIState.update {
            it.copy(currentPasswordFocus = true, currentPasswordVisible = isVisible)
        }
    }
    fun setNewPasswordVisible(isVisible: Boolean) {
        myProfileUIState.update {
            it.copy(newPasswordVisible = !isVisible)
        }
    }
    fun setNewPasswordFocus(isFocus: Boolean, isVisible: Boolean) {
        if (!isFocus) {
            myProfileUIState.update {
                it.copy(newPasswordFocus = false, newPasswordVisible = false)
            }
            return
        }
        myProfileUIState.update {
            it.copy(newPasswordFocus = true, newPasswordVisible = isVisible)
        }
    }

    fun setConfirmPasswordVisible(isVisible: Boolean) {
        myProfileUIState.update {
            it.copy(confirmPasswordVisible = !isVisible)
        }
    }
    fun setConfirmPasswordFocus(isFocus: Boolean, isVisible: Boolean) {
        if (!isFocus) {
            myProfileUIState.update {
                it.copy(confirmPasswordFocus = false, confirmPasswordVisible = false)
            }
            return
        }
        myProfileUIState.update {
            it.copy(confirmPasswordFocus = true, confirmPasswordVisible = isVisible)
        }
    }

    /** LIFECYCLE **/
    init {
        setIsLoading(true, context.getString(R.string.txt_getting_information_employee_and_company))
        getEmployeeWithCompany()
    }

    /** METHODS **/
    private fun getEmployeeWithCompany() {
        viewModelScope.launch(dispatcherIO) {
            when(val result = myProfileUseCases.getEmployeeWithCompany()) {
                is MyProfileResult.GetEmployeeWithCompanySuccess -> {
                    setIsLoading(false)
                    myProfileUIState.update {
                        it.copy(employeeWithCompany = result.employeeWithCompany, enabledButtonChangePassword = true)
                    }
                }
                is MyProfileResult.GetEmployeeWithCompanyError -> {
                    setError(true, result.message)
                }
                else -> {
                    val message = context.getString(R.string.txt_error_get_employee_with_company)
                    setError(true, message)
                }
            }
        }

    }

    fun validateChangePassword() {
        viewModelScope.launch(dispatcherIO) {
            when(val result = myProfileUseCases.validateChangePassword(myProfileUIState.value)) {
                is MyProfileResult.ChangePasswordError -> {
                    setChangePasswordError(true, result.message)
                }
                is MyProfileResult.ChangePasswordSuccess -> {
                    setChangePasswordSuccess(result.success)
                }
                else -> {
                    val message = context.getString(R.string.txt_error_change_password)
                    setChangePasswordError(true, message)
                }
            }
        }
    }

}