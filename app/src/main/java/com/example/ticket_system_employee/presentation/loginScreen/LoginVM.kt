package com.example.ticket_system_employee.presentation.loginScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticket_system_employee.domain.result.login.LoginResult
import com.example.ticket_system_employee.domain.useCases.login.LoginUseCases
import com.example.ticket_system_employee.presentation.commons.models.EmployeeToLogin
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LoginUIState(
    val domainValue: String = "",
    val emailValue: String = "",
    val passwordValue: String = "",
    val enabledButton: Boolean = false,
    val passwordFocus: Boolean = false,
    val passwordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val errorLogin: Boolean = false,
    val successLogin: Boolean = false,
    val message: String = ""
)

class LoginVM(
    private val loginUseCases: LoginUseCases,
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {
    /** Utils **/
    private val context get() = loginUseCases.getContext()

    /** STATES **/
    private val loginUIState = MutableStateFlow(LoginUIState())
    val loginUIStateValues = loginUIState.asStateFlow()

    fun setSuccessLogin(value: Boolean) {
        loginUIState.update { it.copy(successLogin = value) }
    }

    fun setErrorLogin(value: Boolean, message: String) {
        loginUIState.update { it.copy(errorLogin = value,) }
    }

    fun setIsLoading(value: Boolean) {
        loginUIState.update { it.copy(isLoading = value) }
    }

    fun setDomainValue(value: String) {
        loginUIState.update { it.copy(domainValue = value) }
        setEnabledButton()
    }

    fun setEmailValue(value: String) {
        loginUIState.update { it.copy(emailValue = value) }
        setEnabledButton()
    }

    fun setPasswordValue(value: String) {
        loginUIState.update { it.copy(passwordValue = value) }
        setEnabledButton()
    }

    private fun setEnabledButton() {
        val values = loginUIStateValues.value
        val domainValue = values.domainValue.isNotEmpty()
        val emailValue = values.emailValue.isNotEmpty()
        val passwordValue = values.passwordValue.isNotEmpty()
        val enabledButton = domainValue && emailValue && passwordValue
        loginUIState.update { it.copy(enabledButton = enabledButton) }
    }

    fun setPasswordVisible(isVisible: Boolean) {
        loginUIState.update {
            it.copy(passwordVisible = !isVisible)
        }
    }

    fun setPasswordFocus(isFocus: Boolean, isVisible: Boolean) {
        if (!isFocus) {
            loginUIState.update {
                it.copy(passwordFocus = false, passwordVisible = false)
            }
            return
        }
        loginUIState.update {
            it.copy(passwordFocus = true, passwordVisible = isVisible)
        }
    }

    /** LIFECYCLE **/
    init {
        verifyInitialData()
    }
    /** METHODS **/
    private fun verifyInitialData() {
        viewModelScope.launch(dispatcherIO) { loginUseCases.verifyInitialData() }
    }

    fun validateLogin() {
        val uiState = loginUIState.value
        val employeeToLogin = EmployeeToLogin(
            companyName = uiState.domainValue,
            email = uiState.emailValue,
            password = uiState.passwordValue
        )
        viewModelScope.launch(dispatcherIO) {
            when(val result = loginUseCases.validateLogin(employeeToLogin)) {
                is LoginResult.SuccessLogin -> { setSuccessLogin(result.success) }
                is LoginResult.ErrorLogin -> { setErrorLogin(true, result.message) }
            }
        }
    }

}