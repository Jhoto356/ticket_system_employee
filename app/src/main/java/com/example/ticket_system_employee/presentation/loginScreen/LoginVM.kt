package com.example.ticket_system_employee.presentation.loginScreen

import androidx.lifecycle.ViewModel
import com.example.ticket_system_employee.domain.useCases.login.LoginUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class LoginUIState(
    val domainValue: String = "",
    val emailValue: String = "",
    val passwordValue: String = "",
    val enabledButton: Boolean = false,
    val passwordFocus: Boolean = false,
    val passwordVisible: Boolean = false
)

class LoginVM(private val loginUseCases: LoginUseCases): ViewModel() {
    /** STATES **/
    private val loginUIState = MutableStateFlow(LoginUIState())
    val loginUIStateValues = loginUIState.asStateFlow()

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

}